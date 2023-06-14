package edu.wat.tim.lab1.service;

import edu.wat.tim.lab1.model.KoszykProduktEntity;
import edu.wat.tim.lab1.repository.KlientEntityRepository;
import edu.wat.tim.lab1.repository.KoszykEntityRepository;
import edu.wat.tim.lab1.repository.KoszykProduktEntityRepository;
import edu.wat.tim.lab1.repository.ProduktEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.graalvm.polyglot.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;

import java.util.List;


@Service
@Slf4j
public class ScriptService {
    private final KlientEntityRepository klientEntityRepository;
    private final KoszykEntityRepository koszykEntityRepository;
    private final KoszykProduktEntityRepository koszykProduktEntityRepository;
    private final ProduktEntityRepository produktEntityRepository;

    @Autowired
    public ScriptService(KlientEntityRepository klientEntityRepository, KoszykEntityRepository koszykEntityRepository,
                         KoszykProduktEntityRepository koszykProduktEntityRepository,ProduktEntityRepository produktEntityRepository) {
        this.klientEntityRepository= klientEntityRepository;
        this.koszykEntityRepository = koszykEntityRepository;
        this.koszykProduktEntityRepository=koszykProduktEntityRepository;
        this.produktEntityRepository=produktEntityRepository;
    }

    public Object executeScript(String script) throws ScriptExecutionException {
        try {
            // Inicjalizacja środowiska skryptowego (np. GraalVM)
            Context context = Context.create();

            // Wykonanie skryptu
            Value result = context.eval("js", script);

            // Przetworzenie wyniku do odpowiedniego typu (jeśli potrzebne)
            Object processedResult = processScriptResult(result);

            // Zwrócenie wyniku
            return processedResult;
        } catch (Exception e) {
            // Obsługa błędów
            throw new ScriptExecutionException("Script execution failed: " + e.getMessage(), e);
        }
    }

    private Object processScriptResult(Value result) {
        // Przetworzenie wyniku skryptu do odpowiedniego typu lub formy
        // na podstawie potrzeb Twojej aplikacji
        // np. konwersja na String, parsowanie JSON itp.
        return result.asString();
    }

    public int calculateTotalCartValue(long cartId) {
        int totalQuantity = 0;

        List<KoszykProduktEntity> produktyWKoszyku = koszykProduktEntityRepository.findByKoszykId(cartId);

        for (KoszykProduktEntity produkt : produktyWKoszyku) {
            int productQuantity = produkt.getIlosc();
            totalQuantity += productQuantity;
        }

        return totalQuantity;
    }

}
