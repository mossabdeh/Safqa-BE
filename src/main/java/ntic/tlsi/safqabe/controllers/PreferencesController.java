package ntic.tlsi.safqabe.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import ntic.tlsi.safqabe.entities.Preferences;
import ntic.tlsi.safqabe.services.PreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/preferences")
@SecurityRequirement(name = "bearerAuth")
public class PreferencesController {

    @Autowired
    private PreferencesService preferencesService;

    @PostMapping("/for-client/{clientId}")
    public ResponseEntity<Preferences> createPreferencesForClient(@RequestBody Preferences preferences, @PathVariable Long clientId) {
        try {
            Preferences createdPreferences = preferencesService.savePreferencesForClient(preferences, clientId);
            return ResponseEntity.ok(createdPreferences);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<Preferences> getPreferencesForClient(@PathVariable Long clientId) {
        try {
            Preferences preferences = preferencesService.getPreferencesForClient(clientId);
            return ResponseEntity.ok(preferences);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
