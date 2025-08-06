package usd.jedzius.ticket.booking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import usd.jedzius.ticket.booking.app.model.Screening;

public interface ScreeningRepository extends JpaRepository<Screening, Integer> {
}
