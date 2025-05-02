package fr.eni.ludotheque.bo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExemplaryTest {

    @Test
    void createExemplary() {

        Exemplary exemplary = new Exemplary(123456789123L, true);


        assertThat(exemplary).isNotNull();
        assertThat(exemplary.getBarcode()).isEqualTo(123456789123L);

    }
}