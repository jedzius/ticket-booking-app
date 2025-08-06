package usd.jedzius.ticket.booking.app.response.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class SeatTakenException extends RuntimeException{

    public SeatTakenException(String message) {
        super(message);
    }
}