package edu.wat.tim.lab1.repository;

import edu.wat.tim.lab1.model.ProduktEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduktEntityRepository extends JpaRepository<ProduktEntity, Long> {

    List<ProduktEntity> findByNazwaContainingIgnoreCase(String nazwa);
    // Dostępne słowa kluczowe https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords

}
