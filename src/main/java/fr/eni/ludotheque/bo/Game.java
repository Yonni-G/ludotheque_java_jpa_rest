package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "GAMES")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long gameId;

    @NonNull
    private String title;

    @Column(unique = true,  nullable = false,  length = 13)
    private long reference;

    @Column(nullable = false)
    private int minAge;

    @Column(length = 200)
    private String description;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private float pricePerDay;

    @ManyToMany
    @JoinTable(
            name = "JEUX_GENRES", // nom de la table de jointure
            joinColumns = @JoinColumn(name = "jeu_id"), // clé étrangère vers Jeu
            inverseJoinColumns = @JoinColumn(name = "genre_id") // clé étrangère vers Genre
    )
    private Set<Genre> genres = new HashSet<>();
}
