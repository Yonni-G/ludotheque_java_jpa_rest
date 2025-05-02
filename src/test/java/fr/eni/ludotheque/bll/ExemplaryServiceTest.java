package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Exemplary;
import fr.eni.ludotheque.dal.ExemplaryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ExemplaryServiceTest {

    @MockitoBean
    private ExemplaryRepository exemplaryRepository;

    @Autowired
    private ExemplaryService exemplaryService;

    @Test
    @DisplayName("TEST: Modifier l'état d'un exemplaire")
    public void testChangeExemplaryState() {
        Exemplary exemplary = new Exemplary();
        exemplary.setIsRentable(false);

        exemplaryService.changeIsRentableState(exemplary);

        assertThat(exemplary.getIsRentable()).isTrue();
    }
}