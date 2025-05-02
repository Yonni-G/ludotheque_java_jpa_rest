package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Exemplary;
import fr.eni.ludotheque.bo.Game;
import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.dal.ExemplaryRepository;
import fr.eni.ludotheque.dal.GameRepository;
import fr.eni.ludotheque.dal.GenreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GameServiceTest {

    @MockitoBean
    private GameRepository gameRepository;
    @MockitoBean
    private ExemplaryRepository exemplaryRepository;
    @MockitoBean
    private GenreRepository genreRepository;

    @Autowired
    private GameService gameService;
    @Autowired
    private GenreService genreService;

    @Test
    @DisplayName("TEST: Créer un jeu")
    void createGame() {
        Game mockGame = new Game("Test");

        when(gameRepository.save(mockGame)).thenReturn(mockGame);

        Game gameDb = gameService.addGame(mockGame);

        assertThat(gameDb).isNotNull();
        assertThat(gameDb.getGameId()).isNotNull();
        verify(gameRepository, times(1)).save(mockGame);
    }

    @Test
    @DisplayName("TEST: Créer un jeu avec un ou plusieurs exemplaires")
    void testCreateGameWithExemplaries() {
        Game mockGame = new Game("Test");
        when(gameRepository.save(mockGame)).thenReturn(mockGame);

        Exemplary mockExemplary1 = new Exemplary();
        mockExemplary1.setBarcode(1234567891234L);
        mockExemplary1.setIsRentable(false);
        mockExemplary1.setGame(mockGame);

        mockGame.getExemplaries().add(mockExemplary1);
        gameService.addGame(mockGame);

        assertThat(mockExemplary1.getGame()).isNotNull();
        assertThat(mockGame.getExemplaries().size()).isEqualTo(1);

        Exemplary mockExemplary2 = new Exemplary();
        mockExemplary2.setBarcode(2345678912345L);
        mockExemplary2.setIsRentable(false);
        mockExemplary2.setGame(mockGame);

        mockGame.getExemplaries().add(mockExemplary2);
        gameService.addGame(mockGame);

        assertThat(mockExemplary2.getGame()).isNotNull();
        assertThat(mockGame.getExemplaries().size()).isEqualTo(2);

        verify(gameRepository, times(2)).save(mockGame);
    }

    @Test
    @DisplayName("TEST: Créer un jeu avec un ou plusieurs genres")
    void testCreateGameWithGenre() {
        Game mockGame = new Game("Test");
        when(gameRepository.save(mockGame)).thenReturn(mockGame);

        // Ajout d'un genre
        Genre mockGenre1 = new Genre();
        when(genreRepository.save(mockGenre1)).thenReturn(mockGenre1);
        mockGenre1.setLibelle("GenreTest");
        genreService.addGenre(mockGenre1);

        mockGame.getGenres().add(mockGenre1);
        gameService.addGame(mockGame);

        assertThat(mockGame.getGenres()).isNotNull();
        assertThat(mockGame.getGenres().size()).isEqualTo(1);

        // Ajout d'un deuxième genre au même jeu
        Genre mockGenre2 = new Genre();
        when(genreRepository.save(mockGenre2)).thenReturn(mockGenre2);
        mockGenre2.setLibelle("GenreTest2");
        genreService.addGenre(mockGenre2);

        mockGame.getGenres().add(mockGenre2);
        gameService.addGame(mockGame);

        assertThat(mockGame.getGenres()).isNotNull();
        assertThat(mockGame.getGenres().size()).isEqualTo(2);

        verify(gameRepository, times(2)).save(mockGame);
        verify(genreRepository, times(1)).save(mockGenre1);
        verify(genreRepository, times(1)).save(mockGenre2);
    }

    @Test
    @DisplayName("TEST: Obtenir la liste des jeux avec leurs exemplaires")
    public void testGetAllGamesWithExemplaries() {
        Game mockGame = new Game("Test");
        Game mockGame2 = new Game("Test2");

        Exemplary mockExemplary1 = new Exemplary();
        mockExemplary1.setBarcode(12345678912345L);
        mockExemplary1.setIsRentable(true);
        mockExemplary1.setGame(mockGame);
        Exemplary mockExemplary2 = new Exemplary();
        mockExemplary2.setBarcode(2345678912345L);
        mockExemplary2.setIsRentable(true);
        mockExemplary2.setGame(mockGame);
        mockGame.getExemplaries().addAll(List.of(mockExemplary1, mockExemplary2));

        Exemplary mockExemplary3 = new Exemplary();
        mockExemplary3.setBarcode(2345678912345L);
        mockExemplary3.setIsRentable(true);
        mockExemplary3.setGame(mockGame2);
        mockGame2.getExemplaries().add(mockExemplary3);

        List<Game> gameList = List.of(mockGame, mockGame2);
        when(gameRepository.findAll()).thenReturn(gameList);

        List<Game> testGameList = gameService.getAllGames();
        assertThat(testGameList).isNotNull();
        assertThat(testGameList.size()).isEqualTo(2);

        assertThat(mockGame.getExemplaries().size()).isEqualTo(2);
        assertThat(mockGame2.getExemplaries().size()).isEqualTo(1);

        verify(gameRepository, times(1)).findAll();
    }
}