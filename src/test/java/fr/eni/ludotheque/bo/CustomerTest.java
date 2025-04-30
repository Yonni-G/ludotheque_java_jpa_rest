package fr.eni.ludotheque.bo;

import fr.eni.ludotheque.dal.CustomerRepository;
import fr.eni.ludotheque.dal.GenreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class CustomerTest {

    @Autowired
    private CustomerRepository addressRepo;
    @Autowired
    private GenreRepository genreRepo;

    /*
   Test création client
    */
    @Test
    @DisplayName("test de creation d'un client - cas positif")
    public void testCreationClient() {

        // ARRANGE = préparation du test
        Customer client = null;
        client = new Customer("smith", "bob", "0123456789");
        Address address = new Address("27 avenue des rives", "44760", "la bernerie en retz");
        client.setAddress(address);
        // genre
        Genre genre = new Genre("Action2");

        // ACT
        Customer clientBD = addressRepo.save(client);
        Genre genreBD = genreRepo.save(genre);

        // Assert
        assertThat(client).isNotNull();
        assertThat(client.getFirstName()).isEqualTo("smith");
        assertThat(client.getLastName()).isEqualTo("bob");

        // On teste l'ajout d'une adresse
        assertThat(clientBD.getAddress()).isEqualTo(address);
        // On teste l'ajout d'un genre
        assertThat(genreBD).isNotNull();
        assertThat(genreBD.getLibelle()).isEqualTo("Action2");
         /*

        // Arrange


        // Act
        /*Client client = Client.builder()
                .nom("John")
                .prenom("Doe")
                .build();
        assertThat(client).isNotNull();
        assertThat(client.getNom()).isEqualTo("John");
        assertThat(client.getPrenom()).isEqualTo("Doe");

         */
    }

}