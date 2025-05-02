package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Exemplary;
import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.dal.LocationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LocationServiceTest {

    @MockitoBean
    private LocationRepository locationRepository;

    @Autowired
    private LocationService locationService;

    @Test
    @DisplayName("TEST: Créer une location (sortir un exemplaire)")
    public void testAddLocation() {
        Location mockLocation = new Location();
        Exemplary exemplary = new Exemplary();
        exemplary.setBarcode(1234567890123L);
        mockLocation.setExemplary(exemplary);
        when(locationRepository.save(mockLocation)).thenReturn(mockLocation);

        Location locationDB = locationService.addLocation(mockLocation);

        assertThat(locationDB).isEqualTo(mockLocation);
        assertThat(locationDB).isNotNull();
        assertThat(locationDB.getExemplary().getBarcode()).isEqualTo(1234567890123L);

    }

    @Test
    @DisplayName("TEST: édition d'une facture avec le bon montant au retour d'un exemplaire (=retour de location)")
    public void testEditBill() {
        // Test avec une location de moins d'un mois
        Location location = new Location();
        location.setStartingDateAt(LocalDate.of(2025, 5, 1));
        location.setReturningDateAt(LocalDate.of(2025, 5, 11));
        location.setDayPrice(10.0f);

        // Attendu: 10€ la journée * 10 jours = 100€
        locationService.endLocation(location);
        assertThat(location.getBill()).isNotNull();
        assertThat(location.getBill().getAmount()).isEqualTo(100);

        // Test avec une location sur plus d'un mois
        Location location2 = new Location();
        location2.setStartingDateAt(LocalDate.of(2025, 5, 12));
        location2.setReturningDateAt(LocalDate.of(2025, 6, 12));
        location2.setDayPrice(10.0f);

        // Attendu: 10€ la journée * 31 jours = 310€
        locationService.endLocation(location2);
        assertThat(location2.getBill()).isNotNull();
        assertThat(location2.getBill().getAmount()).isEqualTo(310);

    }
}