package fr.eni.ludotheque.bo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GameTest {

    @Test
    void createGameTest() {
        Game game = new Game("Test");

        assertThat(game).isNotNull();
        assertThat(game.getTitle()).isEqualTo("Test");
    }
}