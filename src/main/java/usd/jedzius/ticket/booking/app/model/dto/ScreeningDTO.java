package usd.jedzius.ticket.booking.app.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ScreeningDTO(
        Integer movieId,
        List<LocalDateTime> screeningTimes) {
}