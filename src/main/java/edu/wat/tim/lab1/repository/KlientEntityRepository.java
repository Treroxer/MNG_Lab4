package edu.wat.tim.lab1.repository;

import edu.wat.tim.lab1.model.KlientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KlientEntityRepository extends JpaRepository<KlientEntity, Long> {

    // Dostępne słowa kluczowe https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords

}
