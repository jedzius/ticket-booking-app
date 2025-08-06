package usd.jedzius.ticket.booking.app.model.dto;

import usd.jedzius.ticket.booking.app.model.type.TicketType;

public record TicketDTO(
        String seatCode,
        TicketType ticketType
) {
}