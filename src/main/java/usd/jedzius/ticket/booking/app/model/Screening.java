package usd.jedzius.ticket.booking.app.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "screenings")
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private ScreeningRoom roomId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie pendingMovie;

    @Column
    private LocalDateTime startTime;


    public Screening() {}

    public Screening(Integer id, ScreeningRoom roomId, Movie pendingMovie, LocalDateTime startTime) {
        this.id = id;
        this.roomId = roomId;
        this.pendingMovie = pendingMovie;
        this.startTime = startTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ScreeningRoom getRoomId() {
        return roomId;
    }

    public void setRoomId(ScreeningRoom roomId) {
        this.roomId = roomId;
    }

    public Movie getPendingMovie() {
        return pendingMovie;
    }

    public void setPendingMovie(Movie pendingMovie) {
        this.pendingMovie = pendingMovie;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
