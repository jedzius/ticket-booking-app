package usd.jedzius.ticket.booking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import usd.jedzius.ticket.booking.app.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
