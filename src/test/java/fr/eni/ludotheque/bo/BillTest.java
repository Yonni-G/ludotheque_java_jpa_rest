package fr.eni.ludotheque.bo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class BillTest {

    @Test
    void createBill() {
        Bill bill = new Bill();

        assertThat(bill).isNotNull();
    }
}