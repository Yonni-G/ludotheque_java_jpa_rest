package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Address;
import fr.eni.ludotheque.bo.Customer;
import fr.eni.ludotheque.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(Customer customer);
    Customer setCustomerInfos(Long customerId, CustomerDTO customerDTO);
    List<Customer> getCustomersByLastNameStartingWith(String prefix);
    Customer setCustomerAddress(Long customerId, Address address);
}
