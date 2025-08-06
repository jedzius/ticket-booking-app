package usd.jedzius.ticket.booking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import usd.jedzius.ticket.booking.app.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
