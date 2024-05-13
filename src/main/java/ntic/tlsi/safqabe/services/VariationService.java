package ntic.tlsi.safqabe.services;
import jakarta.transaction.Transactional;
import ntic.tlsi.safqabe.entities.Variation;
import ntic.tlsi.safqabe.entities.VariationOption;
import ntic.tlsi.safqabe.repositories.VariationOptionRepository;
import ntic.tlsi.safqabe.repositories.VariationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VariationService {

    @Autowired
    private VariationRepository variationRepository;
    @Autowired
    private VariationOptionRepository variationOptionRepository;

    // Method to create a new Variation
    public Variation createVariation(String name) {
        Variation variation = new Variation(null, name);
        return variationRepository.save(variation);
    }

    // Method to add a Variation Option linked to a specific Variation
    @Transactional
    public VariationOption addVariationOption(Long variationId, String value) {
        Variation variation = variationRepository.findById(variationId)
                .orElseThrow(() -> new IllegalArgumentException("Variation not found with ID: " + variationId));

        VariationOption variationOption = new VariationOption(null, value, variation);
        variation.getVariationOptions().add(variationOption);  // Add the new option to the variation's set
        return variationOptionRepository.save(variationOption);
    }
}
