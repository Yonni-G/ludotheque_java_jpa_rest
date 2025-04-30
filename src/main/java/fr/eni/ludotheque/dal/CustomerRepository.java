package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
