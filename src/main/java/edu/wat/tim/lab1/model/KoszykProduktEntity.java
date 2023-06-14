package edu.wat.tim.lab1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="koszykprodukt")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class KoszykProduktEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="koszyk_id", nullable=false)
    @JsonIgnore
    private KoszykEntity koszykEntity;

    @ManyToOne
    @JoinColumn(name="produkt_id", nullable=false)
    @JsonIgnore
    private ProduktEntity produktEntity;

    @Column(name = "ilosc")
    private Integer ilosc;
}
