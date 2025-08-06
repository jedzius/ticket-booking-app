package usd.jedzius.ticket.booking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usd.jedzius.ticket.booking.app.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
