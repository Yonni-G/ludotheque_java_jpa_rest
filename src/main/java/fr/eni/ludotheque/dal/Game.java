package fr.eni.ludotheque.dal;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "games")
public class Game {

    private Long game_id;

    private String title;

    private Long reference;

    private int min_age;

    private String description;

    private
}
