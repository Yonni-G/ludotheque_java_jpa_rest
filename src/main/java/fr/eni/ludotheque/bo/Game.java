package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "GAMES")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    @NonNull
    @Column(length = 100)
    private String title;

    @Column(length = 13)
    private long reference;

    @Column(nullable = false)
    private int minAge;

    @Column(nullable = true, length = 255)
    private String description;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private float pricePerDay;

    public Game(@NonNull String title, long reference, int minAge, String description, int duration, float pricePerDay) {
        this.title = title;
        this.reference = reference;
        this.minAge = minAge;
        this.description = description;
        this.duration = duration;
        this.pricePerDay = pricePerDay;
    }
}
