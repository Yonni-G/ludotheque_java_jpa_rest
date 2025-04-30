package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Exemplary;
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

    @Test
    void testGameExemplaryRelation() {

        Game game = new Game("Test");

        Exemplary exemplary = new Exemplary(1234567891234L, false);
        exemplary.setGame(game);

        Exemplary exemplary1 = new Exemplary(2345678912345L, false);
        exemplary1.setGame(game);

        game.getExemplaries().add(exemplary);
        game.getExemplaries().add(exemplary1);

        gameRepository.save(game);

        assertThat(exemplary.getGame()).isNotNull();
        assertThat(exemplary1.getGame()).isNotNull();
        assertThat(game.getExemplaries().size()).isEqualTo(2);
    }
}