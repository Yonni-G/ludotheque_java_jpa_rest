package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Exemplary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExemplaryRepository extends JpaRepository<Exemplary,Long> {
}
