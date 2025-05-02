package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Exemplary;
import fr.eni.ludotheque.bo.Game;
import fr.eni.ludotheque.bo.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GenreRepository genreRepository;

    @Test
    @Transactional
    void createGame() {
        Game game = new Game("Test");

        Game gameDb = gameRepository.save(game);

        assertThat(gameDb).isNotNull();
        assertThat(gameDb.getGameId()).isNotNull();
    }

    @Test
    @Transactional
    void testGameExemplaryRelation() {

        Game game = new Game("Test");

        // Ajout d'exemplaires
        Exemplary exemplary1 = new Exemplary();
        exemplary1.setBarcode(1234567891234L);
        exemplary1.setIsRentable(false);
        exemplary1.setGame(game);

        Exemplary exemplary2 = new Exemplary();
        exemplary2.setBarcode(2345678912345L);
        exemplary2.setIsRentable(false);
        exemplary2.setGame(game);

        game.getExemplaries().add(exemplary1);
        game.getExemplaries().add(exemplary2);

        gameRepository.save(game);

        assertThat(exemplary1.getGame()).isNotNull();
        assertThat(exemplary2.getGame()).isNotNull();
        assertThat(game.getExemplaries().size()).isEqualTo(2);
    }

    @Test
    @Transactional
    void testGameGenreRelation() {
        Game game = new Game("Test");

        // Ajout d'un genre
        Genre genre1 = new Genre();
        genre1.setLibelle("GenreTest");
        genreRepository.save(genre1);

        game.getGenres().add(genre1);
        gameRepository.save(game);

        assertThat(game.getGenres()).isNotNull();
        assertThat(game.getGenres().size()).isEqualTo(1);

        // Ajout d'un deuxième genre au même jeu
        Genre genre2 = new Genre();
        genre2.setLibelle("GenreTest2");
        genreRepository.save(genre2);

        game.getGenres().add(genre2);
        gameRepository.save(game);

        assertThat(game.getGenres()).isNotNull();
        assertThat(game.getGenres().size()).isEqualTo(2);
    }
}