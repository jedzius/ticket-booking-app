package usd.jedzius.ticket.booking.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import usd.jedzius.ticket.booking.app.model.Screening;
import usd.jedzius.ticket.booking.app.model.ScreeningRoom;
import usd.jedzius.ticket.booking.app.model.Seat;
import usd.jedzius.ticket.booking.app.model.dto.SeatDTO;
import usd.jedzius.ticket.booking.app.repository.ScreeningRepository;
import usd.jedzius.ticket.booking.app.repository.SeatRepository;
import usd.jedzius.ticket.booking.app.response.ActionResponse;
import usd.jedzius.ticket.booking.app.response.exception.EntityNotFoundException;
import usd.jedzius.ticket.booking.app.service.SeatService;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatsController {

    private final SeatService seatService;
    private final ScreeningRepository screeningRepository;
    private final SeatRepository seatRepository;

    public SeatsController(SeatService seatService, ScreeningRepository screeningRepository, SeatRepository seatRepository) {
        this.seatService = seatService;
        this.screeningRepository = screeningRepository;
        this.seatRepository = seatRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ActionResponse> createSeat(@RequestParam(name = "seat_code") String seatCode, @RequestParam(name = "room_id") int roomId) {
        Screening screening = screeningRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException("Screening not found."));
        ScreeningRoom room = screening.getRoomId();
        Seat seat = new Seat();
        seat.setSeatCode(seatCode);
        seat.setScreeningRoom(room);
        seatRepository.save(seat);

        return ResponseEntity.status(200).body(new ActionResponse("Seat has been created"));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<SeatDTO>> getSeats(@RequestParam(name = "room_id") Integer roomId) {
        return ResponseEntity.ok(seatService.getSeatsDTO(roomId));
    }
}
