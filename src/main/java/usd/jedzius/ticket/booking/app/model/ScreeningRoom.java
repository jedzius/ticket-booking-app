package usd.jedzius.ticket.booking.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "screening-rooms")
public class ScreeningRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    public ScreeningRoom() {}

    public ScreeningRoom(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
