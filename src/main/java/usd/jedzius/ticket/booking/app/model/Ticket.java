package usd.jedzius.ticket.booking.app.model;

import jakarta.persistence.*;
import usd.jedzius.ticket.booking.app.model.type.TicketType;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Reservation reservation;

    @ManyToOne
    private Seat seat;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketType type;

    public Ticket() { }

    public Ticket(Integer id, Reservation reservation, Seat seat, TicketType type) {
        this.id = id;
        this.reservation = reservation;
        this.seat = seat;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }
}
