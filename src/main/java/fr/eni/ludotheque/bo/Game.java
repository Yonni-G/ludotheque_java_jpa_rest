package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    // Optionnel. On décide de mettre une relation bidirectionnelle et qu'on peut avoir besoin de la liste des exemplaires depuis un jeu
    // Si on efface un jeu, on efface ses exemplaires
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Exemplary> exemplaries = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "GAMES_GENRES", // nom de la table de jointure
            joinColumns = @JoinColumn(name = "game_id"), // clé étrangère vers Jeu
            inverseJoinColumns = @JoinColumn(name = "genre_id") // clé étrangère vers Genre
    )
    private List<Genre> genres = new ArrayList<>();


}
