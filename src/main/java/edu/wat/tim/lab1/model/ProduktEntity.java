package edu.wat.tim.lab1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="produkt")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProduktEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "nazwa")
    private String nazwa;

    @Column(name = "jednostka_miary")
    private String jednostka_miary;

    @OneToMany(mappedBy = "produktEntity", cascade = CascadeType.MERGE)
    private List<KoszykProduktEntity> koszykproduktEntities = new ArrayList<>();

}
