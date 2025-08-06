package usd.jedzius.ticket.booking.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private ScreeningRoom screeningRoom;

    @Pattern(regexp = "[a-fA-F]{2}", message = "Invalid seat code.")
    @Column(nullable = false)
    private String seatCode;

    public Seat() { }

    public Seat(Integer id, ScreeningRoom screeningRoom, String seatCode) {
        this.id = id;
        this.screeningRoom = screeningRoom;
        this.seatCode = seatCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ScreeningRoom getScreeningRoom() {
        return screeningRoom;
    }

    public void setScreeningRoom(ScreeningRoom screeningRoom) {
        this.screeningRoom = screeningRoom;
    }

    public @Pattern(regexp = "[a-fA-F]{2}", message = "Invalid seat code.") String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(@Pattern(regexp = "[a-fA-F]{2}", message = "Invalid seat code.") String seatCode) {
        this.seatCode = seatCode;
    }
}
