package usd.jedzius.ticket.booking.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import usd.jedzius.ticket.booking.app.model.dto.SeatDTO;
import usd.jedzius.ticket.booking.app.service.SeatService;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatsController {

    private final SeatService seatService;

    public SeatsController(SeatService seatService) {
        this.seatService = seatService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<SeatDTO>> getSeats(@RequestParam(name = "room_id") Integer roomId) {
        return ResponseEntity.ok(seatService.getUnavailableSeats(roomId));
    }
}
