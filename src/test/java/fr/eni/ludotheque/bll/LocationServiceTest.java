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
}