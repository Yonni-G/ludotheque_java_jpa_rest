package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Address;
import fr.eni.ludotheque.bo.Customer;
import fr.eni.ludotheque.dal.CustomerRepository;
import fr.eni.ludotheque.dto.CustomerDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Test
    @DisplayName("Rechercher les clients dont le nom commence par un préfixe - cas positif")
    public void testRechercherClientDontNomCommenceParCasPositif() {
        // Arrange
        Customer customer1 = new Customer("n1", "yonni1", "e1", "0123456789",
                new Address("street1", "12345", "ville1"));
        Customer customer2 = new Customer("n2", "yonni2", "e2", "1123456789",
                new Address("street2", "12345", "ville2"));
        String prefixToFind = "yonni";

        List<Customer> mockedResult = Arrays.asList(customer1, customer2);

        // Mock du repository pour retourner ces 2 clients si le préfixe correspond
        Mockito.when(customerRepository.findByLastNameStartingWith(prefixToFind))
                .thenReturn(mockedResult);

        // Act
        List<Customer> customers = customerService.getCustomersByLastNameStartingWith(prefixToFind);

        // Assert
        assertNotNull(customers);
        assertFalse(customers.isEmpty());
        assertEquals(2, customers.size());
        for (Customer c : customers) {
            assertTrue(c.getLastName().startsWith(prefixToFind));
        }
    }

}