package usd.jedzius.ticket.booking.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usd.jedzius.ticket.booking.app.model.dto.ScreeningDTO;
import usd.jedzius.ticket.booking.app.service.ScreeningService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final ScreeningService screeningService;

    public MovieController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ScreeningDTO>> getScreeningBetweenTime(@RequestParam("start") LocalDateTime from, @RequestParam("end") LocalDateTime to) {
        return ResponseEntity.ok(screeningService.getAllScreenings(from, to));
    }
}
