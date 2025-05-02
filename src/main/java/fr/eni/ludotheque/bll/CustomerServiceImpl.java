package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Address;
import fr.eni.ludotheque.bo.Customer;
import fr.eni.ludotheque.dal.CustomerRepository;
import fr.eni.ludotheque.dto.CustomerDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer setCustomerInfos(Long id, CustomerDTO dto) {
        return customerRepository.findById(id)
                .map(customer -> {
                    // MAJ des infos
                    customer.setFirstName(dto.getFirstName());
                    customer.setLastName(dto.getLastName());
                    customer.setEmail(dto.getEmail());
                    customer.setPhone(dto.getPhone());
                    customer.setAddress(dto.getAddress());
                    return customerRepository.save(customer);
                })
                .orElseThrow(() -> new EntityNotFoundException("Client pas trouvé avec l'id: " + id));
    }

    public Customer setCustomerAddress(Long id, Address address) {
        return customerRepository.findById(id)
                .map(customer -> {
                    // MAJ de l'adresse
                    customer.setAddress(address);
                    return customerRepository.save(customer);
                })
                .orElseThrow(() -> new EntityNotFoundException("Client pas trouvé avec l'id: " + id));
    }

    public List<Customer> getCustomersByLastNameStartingWith(String prefix){
        return customerRepository.findByLastNameStartingWith(prefix);
    }

}
