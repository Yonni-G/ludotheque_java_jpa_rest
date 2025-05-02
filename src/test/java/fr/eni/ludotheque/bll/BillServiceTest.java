package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Bill;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BillServiceTest {

    @Autowired
    private BillService billService;

    @Test
    @DisplayName(("Test: Indiquer qu'une facture est payée"))
    void testBillPaid() {
        Bill bill = new Bill();
        billService.setBillPaid(bill);

        assertThat(bill.getPaidAt()).isNotNull();
        
    }
}