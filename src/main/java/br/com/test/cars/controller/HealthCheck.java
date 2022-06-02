package br.com.test.cars.controller;

import br.com.test.cars.model.Health;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * HealthCheck to verify if resources are up.
 */
@Slf4j
@RestController
public final class HealthCheck {

    @GetMapping(value = {"/health", "/", ""})
    public ResponseEntity health(final Locale locale) {
        log.info("Welcome home. The client locale is " + locale.toString());
        return ResponseEntity.ok(new Health("UP", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", locale))));
    }
}
