package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastNameStartingWith(String prefix);
}
