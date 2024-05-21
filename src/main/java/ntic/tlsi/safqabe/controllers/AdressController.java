package ntic.tlsi.safqabe.controllers;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import ntic.tlsi.safqabe.entities.Adress;
import ntic.tlsi.safqabe.services.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/addresses")

@SecurityRequirement(name = "bearerAuth")
public class AdressController {

    @Autowired
    private AdressService adressService;

    @GetMapping
    public List<Adress> getAllAdresses() {
        return adressService.getAllAdresses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adress> getAdressById(@PathVariable Long id) {
        Optional<Adress> adress = adressService.getAdressById(id);
        if (adress.isPresent()) {
            return ResponseEntity.ok(adress.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/for-client/{clientId}")
    public ResponseEntity<Adress> createAdressForClient(@RequestBody Adress adress, @PathVariable Long clientId) {
        try {
            Adress createdAdress = adressService.saveAdressForClient(adress, clientId);
            return ResponseEntity.ok(createdAdress);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/for-supplier/{supplierId}")
    public ResponseEntity<Adress> createAdressForSupplier(@RequestBody Adress adress, @PathVariable Long supplierId) {
        try {
            Adress createdAdress = adressService.saveAdressForSupplier(adress, supplierId);
            return ResponseEntity.ok(createdAdress);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<Set<Adress>> getAddressesForClient(@PathVariable Long clientId) {
        try {
            Set<Adress> addresses = adressService.getAddressesForClient(clientId);
            return ResponseEntity.ok(addresses);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adress> updateAdress(@PathVariable Long id, @RequestBody Adress adressDetails) {
        try {
            Adress updatedAdress = adressService.updateAdress(id, adressDetails);
            return ResponseEntity.ok(updatedAdress);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdress(@PathVariable Long id) {
        try {
            adressService.deleteAdress(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
