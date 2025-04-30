package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @Test
    void createGame() {
        Game game = new Game("Test");

        Game gameDb = gameRepository.save(game);

        assertThat(gameDb).isNotNull();
        assertThat(gameDb.getGameId()).isNotNull();
    }
}