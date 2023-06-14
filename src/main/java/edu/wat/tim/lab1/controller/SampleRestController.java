package edu.wat.tim.lab1.controller;

import edu.wat.tim.lab1.model.KoszykEntity;
import edu.wat.tim.lab1.model.KlientEntity;
import edu.wat.tim.lab1.model.ProduktEntity;
import edu.wat.tim.lab1.model.KoszykProduktEntity;
import edu.wat.tim.lab1.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SampleRestController {

    private final SampleService service;


    @PostMapping("/klient")
    public ResponseEntity<KlientEntity> createParentEntity(@RequestBody KlientEntity entity) {
        KlientEntity savedEntity = service.createParentEntity(entity);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @GetMapping("/produkty")
    public ResponseEntity<List<ProduktEntity>> searchProdukty(@RequestParam("nazwa") String nazwa) {
        List<ProduktEntity> produkty = service.getAllEntitiesByNazwa(nazwa);
        return new ResponseEntity<>(produkty, HttpStatus.OK);
    }

    @PostMapping("/klient/{id}/koszyk")
    public ResponseEntity<KoszykEntity> addChildEntity(@RequestBody KoszykEntity entity, @PathVariable(value = "id") Long id) {
        KoszykEntity savedEntity = service.addChildEntity(entity, id);
        return new ResponseEntity<>(savedEntity, HttpStatus.OK);
    }

    @PostMapping("/koszyk/{id}/produkt")
    public ResponseEntity<ProduktEntity> addProductToCart(@PathVariable Long id, @RequestBody ProduktEntity produktEntity, @RequestParam("ilosc") Integer ilosc) {
        ProduktEntity updatedProdutEntity = service.addProdukt(produktEntity, id, ilosc);
        return new ResponseEntity<>(updatedProdutEntity, HttpStatus.OK);
    }

    @DeleteMapping("/koszyk/{koszykId}/produkt/{produktId}")
    public ResponseEntity<String> deleteProductFromCart(@PathVariable Long koszykId, @PathVariable Long produktId) {
        service.deleteProductFromCart(koszykId, produktId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/koszyk/{koszykId}/produkt/{produktId}")
    public ResponseEntity<Void> changeProductQuantityInCart(@PathVariable Long koszykId, @PathVariable Long produktId, @RequestParam("nowaIlosc") Integer nowaIlosc) {
        service.changeProductQuantityInCart(koszykId, produktId, nowaIlosc);
        return ResponseEntity.ok().build();
    }
}
