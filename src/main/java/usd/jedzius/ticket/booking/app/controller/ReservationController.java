package usd.jedzius.ticket.booking.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usd.jedzius.ticket.booking.app.model.Reservation;
import usd.jedzius.ticket.booking.app.model.dto.ReservationDTO;
import usd.jedzius.ticket.booking.app.model.dto.TicketDTO;
import usd.jedzius.ticket.booking.app.repository.ReservationRepository;
import usd.jedzius.ticket.booking.app.response.ActionResponse;
import usd.jedzius.ticket.booking.app.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationRepository reservationRepository;

    public ReservationController(ReservationService reservationService, ReservationRepository reservationRepository) {
        this.reservationService = reservationService;
        this.reservationRepository = reservationRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ActionResponse> createReservation(@RequestBody ReservationDTO reservationDTO) {
        return ResponseEntity.ok(reservationService.createReservation(reservationDTO));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<Reservation> reservationList = reservationRepository.findAll();
        List<ReservationDTO> reservationDTOList = reservationList.stream()
                .map(reservation -> new ReservationDTO(
                        reservation.getName(),
                        reservation.getSurname(),
                        reservation.getScreening().getRoomId().getId(), reservation.getTickets()
                        .stream()
                        .map(ticket -> new TicketDTO(ticket.getSeat().getSeatCode(), ticket.getType()))
                        .toList()))
                .toList();
        return ResponseEntity.ok(reservationDTOList);
    }
}
