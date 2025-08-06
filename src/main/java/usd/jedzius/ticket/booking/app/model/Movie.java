package usd.jedzius.ticket.booking.app.model;

import jakarta.persistence.*;
import usd.jedzius.ticket.booking.app.model.type.MovieGenre;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MovieGenre genre;

    @Column(nullable = false)
    private double rating;

    @Column(nullable = false)
    private int durationTime;

    public Movie() { }

    public Movie(Integer id, String title, String description, MovieGenre genre, double rating, int durationTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.rating = rating;
        this.durationTime = durationTime;
    }

    public Movie(String title, String description, MovieGenre genre, double rating, int durationTime) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.rating = rating;
        this.durationTime = durationTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(int durationTime) {
        this.durationTime = durationTime;
    }
}
