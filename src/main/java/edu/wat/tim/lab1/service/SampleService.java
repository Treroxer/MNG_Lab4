package edu.wat.tim.lab1.service;

import edu.wat.tim.lab1.model.KoszykEntity;
import edu.wat.tim.lab1.model.KlientEntity;
import edu.wat.tim.lab1.model.KoszykProduktEntity;
import edu.wat.tim.lab1.model.ProduktEntity;
import edu.wat.tim.lab1.repository.KoszykEntityRepository;
import edu.wat.tim.lab1.repository.KlientEntityRepository;
import edu.wat.tim.lab1.repository.KoszykProduktEntityRepository;
import edu.wat.tim.lab1.repository.ProduktEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final KlientEntityRepository klientEntityRepository;
    private final KoszykEntityRepository koszykEntityRepository;
    private final ProduktEntityRepository produktEntityRepository;
    private final KoszykProduktEntityRepository koszykProduktEntityRepository;


    public KlientEntity createParentEntity(KlientEntity entity) {
        return klientEntityRepository.save(entity);
    }

    public List<KlientEntity> getAllEntities() {
        return klientEntityRepository.findAll();
    }

    public KoszykEntity addChildEntity(KoszykEntity koszykEntity, Long parentId) {
        KlientEntity klientEntity = klientEntityRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono encji o id " + parentId));
        koszykEntity.setKlientEntity(klientEntity);
        return koszykEntityRepository.save(koszykEntity);
    }

    public void deleteChildEntity(Long childId) {
        koszykEntityRepository.deleteById(childId);
    }

    public KlientEntity updateEntity(KlientEntity updatedEntity, Long id) {
        KlientEntity entity = klientEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono encji o id " + id));

       // entity.setParentValue(updatedEntity.getParentValue());
        return klientEntityRepository.save(entity);
    }

    public List<ProduktEntity> getAllEntitiesByNazwa(String nazwa) {
        return produktEntityRepository.findByNazwaContainingIgnoreCase(nazwa);
    }

    public ProduktEntity addProdukt(ProduktEntity entity, long id, Integer quantity) {
        KoszykEntity koszykEntity = koszykEntityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("KoszykEntity not found with id: " + id));
        entity = produktEntityRepository.save(entity);
        KoszykProduktEntity koszykProduktEntity = new KoszykProduktEntity();
        koszykProduktEntity.setProduktEntity(entity);
        koszykProduktEntity.setKoszykEntity(koszykEntity);
        koszykProduktEntity.setIlosc(quantity);
        koszykProduktEntityRepository.save(koszykProduktEntity);
        return entity;
    }

    public void deleteProductFromCart(Long koszykId, Long produktId) {
        KoszykEntity koszykEntity = koszykEntityRepository.findById(koszykId)
                .orElseThrow(() -> new EntityNotFoundException("KoszykEntity not found with id: " + koszykId));

        ProduktEntity produktEntity = produktEntityRepository.findById(produktId)
                .orElseThrow(() -> new EntityNotFoundException("ProduktEntity not found with id: " + produktId));

        List<KoszykProduktEntity> koszykProduktEntities = koszykProduktEntityRepository
                .findByKoszykEntityAndProduktEntity(koszykEntity, produktEntity);

        if (koszykProduktEntities.isEmpty()) {
            throw new EntityNotFoundException("KoszykProduktEntity not found with koszykId: " + koszykId + " and produktId: " + produktId);
        }
        koszykProduktEntityRepository.deleteAll(koszykProduktEntities);
        produktEntityRepository.deleteById(produktId);
    }

    public void changeProductQuantityInCart(Long koszykId, Long produktId, Integer newQuantity) {
        KoszykEntity koszykEntity = koszykEntityRepository.findById(koszykId)
                .orElseThrow(() -> new EntityNotFoundException("KoszykEntity not found with id: " + koszykId));

        ProduktEntity produktEntity = produktEntityRepository.findById(produktId)
                .orElseThrow(() -> new EntityNotFoundException("ProduktEntity not found with id: " + produktId));

        List<KoszykProduktEntity> koszykProduktEntities = koszykProduktEntityRepository
                .findByKoszykEntityAndProduktEntity(koszykEntity, produktEntity);

        if (koszykProduktEntities.isEmpty()) {
            throw new EntityNotFoundException("KoszykProduktEntity not found with cartId: " + koszykId + " and productId: " + produktId);
        }

        for (KoszykProduktEntity koszykProduktEntity : koszykProduktEntities) {
            koszykProduktEntity.setIlosc(newQuantity);
        }

        koszykProduktEntityRepository.saveAll(koszykProduktEntities);
    }
}
