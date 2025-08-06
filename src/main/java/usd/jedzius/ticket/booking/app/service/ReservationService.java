package usd.jedzius.ticket.booking.app.service;

import org.springframework.stereotype.Service;

import usd.jedzius.ticket.booking.app.model.Reservation;
import usd.jedzius.ticket.booking.app.model.Screening;
import usd.jedzius.ticket.booking.app.model.Seat;
import usd.jedzius.ticket.booking.app.model.Ticket;
import usd.jedzius.ticket.booking.app.model.dto.ReservationDTO;
import usd.jedzius.ticket.booking.app.model.dto.TicketDTO;
import usd.jedzius.ticket.booking.app.repository.ReservationRepository;
import usd.jedzius.ticket.booking.app.repository.ScreeningRepository;
import usd.jedzius.ticket.booking.app.repository.SeatRepository;
import usd.jedzius.ticket.booking.app.response.ActionResponse;
import usd.jedzius.ticket.booking.app.response.exception.EntityNotFoundException;
import usd.jedzius.ticket.booking.app.response.exception.ReservationTooLateException;
import usd.jedzius.ticket.booking.app.response.exception.SeatTakenException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ReservationService {
    private final ScreeningRepository screeningRepository;
    private final SeatService seatService;
    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;

    public ReservationService(ScreeningRepository screeningRepository, SeatService seatService, ReservationRepository reservationRepository, SeatRepository seatRepository) {
        this.screeningRepository = screeningRepository;
        this.seatService = seatService;
        this.reservationRepository = reservationRepository;
        this.seatRepository = seatRepository;
    }

    public ActionResponse createReservation(ReservationDTO dto) {
        final Screening screening = screeningRepository.findById(dto.screeningId()).orElseThrow(() -> new EntityNotFoundException("Screening not found."));
        final LocalDateTime now = LocalDateTime.now();

        if (Duration.between(now, screening.getStartTime()).toMinutes() < 15)
            throw new ReservationTooLateException("Reservations are only allowed up to 15 minutes before screening start time.");

        final List<TicketDTO> ticketDTOS = dto.tickets();
        final List<Seat> unavailableSeats = seatService.getSeats(screening.getRoomId().getId());

        for (TicketDTO ticketDTO : ticketDTOS) {
            for (Seat seat : unavailableSeats) {
                if(seat.getSeatCode().equalsIgnoreCase(ticketDTO.seatCode()))
                    throw new SeatTakenException("Seat already taken.");
            }
        }

        final Reservation reservation = new Reservation();
        reservation.setName(dto.name());
        reservation.setSurname(dto.surname());
        reservation.setReservationTime(now);
        reservation.setExpirationTime(screening.getStartTime().minusMinutes(15));
        reservation.setScreening(screening);

        final List<Ticket> tickets = new ArrayList<>();
        double totalPrice = 0.0;
        for (TicketDTO ticketDTO : ticketDTOS) {
            Ticket ticket = new Ticket();
            ticket.setReservation(reservation);
            ticket.setType(ticketDTO.ticketType());

            totalPrice += ticketDTO.ticketType().getPrice();

            Seat seat = new Seat();
            seat.setScreeningRoom(screening.getRoomId());
            seat.setSeatCode(ticketDTO.seatCode());

            ticket.setSeat(seat);

            tickets.add(ticket);

            seatRepository.save(seat);
        }

        reservation.setTotalPrice(totalPrice);
        reservation.setTickets(new HashSet<>(tickets));
        reservationRepository.save(reservation);

        return new ActionResponse("Reservation has been created!");
    }
}
