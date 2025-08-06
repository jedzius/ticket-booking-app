package usd.jedzius.ticket.booking.app.model.dto;

import java.util.List;

public record ReservationDTO(
        String name,
        String surname,
        Integer screeningId,
        List<TicketDTO> tickets
) {
}