package fr.eni.ludotheque.bo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class GameTest {

    @Test
    @DisplayName("Test: Create a new game")
    public void createGameTest() {

        Game game = new Game("Skyjo", 1234567890123L, 13, "", 12, 5.0f);

        assertThat(game).isNotNull();
        assertThat(game.getTitle()).isEqualTo("Skyjo");

    }
}