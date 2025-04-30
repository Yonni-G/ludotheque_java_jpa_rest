package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Game;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @Test
    @DisplayName("Test: create a game with repository")
    public void createGameWithRepoTest() {
        Game game = new Game("Skyjo", 1234567890123L, 13, "", 12, 5.0f);

        Game gameSaved = gameRepository.save(game);

        Game gameDb = gameRepository.findById(gameSaved.getGameId()).orElse(null);

        assertThat(gameDb).isNotNull();
        assertThat(gameDb.getGameId()).isNotNull();
    }
}