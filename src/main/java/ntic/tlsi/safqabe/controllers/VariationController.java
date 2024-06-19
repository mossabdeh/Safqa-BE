package ntic.tlsi.safqabe.controllers;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearerAuth")
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
    public ResponseEntity<Variation> createVariation(@RequestParam String name, @RequestParam Long categoryId) {
        Variation variation = variationService.createVariation(name, categoryId);  // Add categoryId parameter
        return ResponseEntity.status(HttpStatus.CREATED).body(variation);
    }

    // Endpoint to find variations by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Variation>> getVariationsByCategory(@PathVariable Long categoryId) {
        List<Variation> variations = variationService.findVariationsByCategory(categoryId);
        return ResponseEntity.ok(variations);
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
