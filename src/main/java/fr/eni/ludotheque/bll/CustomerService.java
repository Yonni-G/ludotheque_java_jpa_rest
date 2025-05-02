package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Customer;
import fr.eni.ludotheque.dto.CustomerDTO;

public interface CustomerService {
    Customer addCustomer(Customer customer);
    Customer setCustomerInfos(Long customerId, CustomerDTO customerDTO);
}
