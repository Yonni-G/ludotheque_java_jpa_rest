package fr.eni.ludotheque.bo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BillTest {

    @Test
    void createBill() {
        Bill bill = new Bill(new Date());

        assertThat(bill).isNotNull();
        assertThat(bill.getPaidAt()).isNotNull();
    }
}