package ntic.tlsi.safqabe.services;
import jakarta.transaction.Transactional;
import ntic.tlsi.safqabe.dtos.VariationDetailDTO;
import ntic.tlsi.safqabe.entities.Variation;
import ntic.tlsi.safqabe.entities.VariationOption;
import ntic.tlsi.safqabe.repositories.VariationOptionRepository;
import ntic.tlsi.safqabe.repositories.VariationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VariationService {

    @Autowired
    private VariationRepository variationRepository;
    @Autowired
    private VariationOptionRepository variationOptionRepository;
    private final ModelMapper modelMapper = new ModelMapper();

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

    // Method to fetch all Variation details
    public List<Variation> getAllVariationDetails() {
        return variationRepository.findAllVariationsWithOptions();
    }


    private VariationDetailDTO convertToDTO(Variation variation) {
        VariationDetailDTO dto = modelMapper.map(variation, VariationDetailDTO.class);
        // Since VariationDetailDTO includes details from VariationOption, you might need a custom mapping logic here
        if (variation.getVariationOptions() != null && !variation.getVariationOptions().isEmpty()) {
            VariationOption option = variation.getVariationOptions().iterator().next(); // Assuming you want the first one or some logic here
            dto.setVariationOptionId(option.getId());
            dto.setVariationOptionValue(option.getVariationValue());
        }
        return dto;
    }

}
