package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Address;
import fr.eni.ludotheque.bo.Customer;
import fr.eni.ludotheque.dal.CustomerRepository;
import fr.eni.ludotheque.dto.CustomerDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@Transactional
class CustomerServiceIntegrationTest {

    @Autowired

    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Modification infos client cas positif")
    public void testModifierInfosClientCasPositif() {
        //Arrange

        // on créé un client avec son adresse
        Address adresse = new Address("rue des Cormorans", "44860", "Saint Aignan Grand Lieu");
        Customer customer = new Customer("n1", "p1", "e1", "0123456789", adresse);
        Customer customerDB = customerRepository.save(customer);
        //System.out.println(customerDB);

        // on cree un clientDTO avec les nouvelles infos
        CustomerDTO customerDTO = new CustomerDTO("n1", "p1", "e1", "tel1", new Address("27 avenue des rives", "44760", "la bernerie en retz"));


        //Act
        Customer customerSaved = customerService.setCustomerInfos(customerDB.getCustomerId(), customerDTO);

        //Assert
        assertThat(customerSaved.getCustomerId()).isNotNull();
        assertThat(customerSaved.getAddress().getPostalCode()).isEqualTo("44760");

    }

    @Test
    @DisplayName("Modification infos client cas négatif (client non-trouvé)")
    public void testModifierInfosClientCasNegatif() {
        //Arrange
        Long nonExistentId = 99L;

        // on cree un clientDTO avec les nouvelles infos
        CustomerDTO customerDTO = new CustomerDTO("n1", "p1", "e1", "tel1", new Address("27 avenue des rives", "44760", "la bernerie en retz"));

        assertThatThrownBy(() -> customerService.setCustomerInfos(nonExistentId, customerDTO))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Client pas trouvé");

    }

}