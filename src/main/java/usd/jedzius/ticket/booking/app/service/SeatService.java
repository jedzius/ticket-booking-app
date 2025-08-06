package usd.jedzius.ticket.booking.app.service;

import org.springframework.stereotype.Service;
import usd.jedzius.ticket.booking.app.model.Seat;
import usd.jedzius.ticket.booking.app.model.dto.SeatDTO;
import usd.jedzius.ticket.booking.app.repository.SeatRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<SeatDTO> getUnavailableSeats(Integer roomId) {
        final List<Seat> seatList = seatRepository.findAll();

        return seatList.stream()
                .filter(seat -> Objects.equals(seat.getId(), roomId))
                .map(seat -> new SeatDTO(seat.getSeatCode()))
                .collect(Collectors.toList());
    }
}
