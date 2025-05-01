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
@Table(name = "EXEMPLARIES")
public class Exemplary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exemplaryId;

    @NonNull
    @Column(nullable = false, unique = true, length = 13)
    private Long barcode;

    @NonNull
    @Column(nullable = false)
    private Boolean isRentable;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    @NonNull
    private Game game;
}
