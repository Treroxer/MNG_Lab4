package edu.wat.tim.lab1.controller;

import edu.wat.tim.lab1.service.ScriptExecutionException;
import edu.wat.tim.lab1.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/script")
public class ScriptController {
    private final ScriptService scriptService;

    @Autowired
    public ScriptController(ScriptService scriptService) {
        this.scriptService = scriptService;
    }

    @PostMapping("/execute")
    public ResponseEntity<Object> executeScript(@RequestBody String script) {
        try {
            Object result = scriptService.executeScript(script);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (ScriptExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
