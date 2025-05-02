package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Address;
import fr.eni.ludotheque.bo.Customer;
import fr.eni.ludotheque.dal.CustomerRepository;
import fr.eni.ludotheque.dto.CustomerDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @MockitoBean
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Ajout d'un client cas positif")
    public void testAjouterClientCasPositif() {
        //Arrange
        Address adresse = new Address("rue des Cormorans", "44860", "Saint Aignan Grand Lieu");
        Customer customer = new Customer("n1", "p1", "e1", "0123456789", adresse);
        //CustomerDTO clientDto = new CustomerDTO("n1", "p1", "e1", "tel1", "rue des Cormorans", "44860", "Saint Aignan Grand Lieu");

        org.mockito.Mockito.doAnswer((invocation) -> {
            Customer cli = invocation.getArgument(0);
            cli.setCustomerId(999L);
            return cli;
        }).when(customerRepository).save(customer);

        //Act
        customerService.addCustomer(customer);

        //Assert
        assertThat(customer.getCustomerId()).isNotNull();
        assertThat(customer.getCustomerId()).isEqualTo(999L);

    }
}