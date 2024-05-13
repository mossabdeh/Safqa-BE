package ntic.tlsi.safqabe.controllers;


import ntic.tlsi.safqabe.entities.Variation;

import ntic.tlsi.safqabe.entities.VariationOption;
import ntic.tlsi.safqabe.services.VariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/variations")
//@CrossOrigin("*")
public class VariationController {
    @Autowired
    private  VariationService variationService;


    @GetMapping("/details")
    public ResponseEntity<List<Variation>> getAllVariationDetails() {
        List<Variation> variations = variationService.getAllVariationDetails();
        return ResponseEntity.ok(variations);
    }

    // Endpoint to create a new variation
    @PostMapping
    public ResponseEntity<Variation> createVariation(@RequestParam String name) {
        Variation variation = variationService.createVariation(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(variation);
    }



    // Endpoint to add an option to a specific variation
    @PostMapping("/{variationId}/options")
    public ResponseEntity<VariationOption> addVariationOption(@PathVariable Long variationId, @RequestParam String value) {
        try {
            VariationOption variationOption = variationService.addVariationOption(variationId, value);
            return ResponseEntity.status(HttpStatus.CREATED).body(variationOption);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
