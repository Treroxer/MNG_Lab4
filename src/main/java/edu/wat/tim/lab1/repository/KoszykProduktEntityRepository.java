package edu.wat.tim.lab1.repository;

import edu.wat.tim.lab1.model.KoszykEntity;
import edu.wat.tim.lab1.model.KoszykProduktEntity;
import edu.wat.tim.lab1.model.ProduktEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KoszykProduktEntityRepository extends JpaRepository<KoszykProduktEntity, Long> {

    List<KoszykProduktEntity> findByKoszykEntityAndProduktEntity(KoszykEntity koszykentity, ProduktEntity produktEntity);

    List<KoszykProduktEntity> findByKoszykEntityId(long cartId);

    List<KoszykProduktEntity> findByKoszykId(long cartId);

    // Dostępne słowa kluczowe https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords

}
