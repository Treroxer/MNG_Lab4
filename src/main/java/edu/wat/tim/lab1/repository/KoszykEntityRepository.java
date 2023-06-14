package edu.wat.tim.lab1.repository;

import edu.wat.tim.lab1.model.KoszykEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KoszykEntityRepository extends JpaRepository<KoszykEntity, Long> {
}
