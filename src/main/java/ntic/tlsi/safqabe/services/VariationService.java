package ntic.tlsi.safqabe.services;
import jakarta.transaction.Transactional;
import ntic.tlsi.safqabe.dtos.VariationDetailDTO;
import ntic.tlsi.safqabe.entities.Category;
import ntic.tlsi.safqabe.entities.Variation;
import ntic.tlsi.safqabe.entities.VariationOption;
import ntic.tlsi.safqabe.repositories.CategoryRepository;
import ntic.tlsi.safqabe.repositories.VariationOptionRepository;
import ntic.tlsi.safqabe.repositories.VariationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VariationService {

    @Autowired
    private VariationRepository variationRepository;
    @Autowired
    private VariationOptionRepository variationOptionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    // Method to create a new Variation
    public Variation createVariation(String name, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + categoryId));
        Variation variation = new Variation(null, name, category);
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
    @Transactional
    public List<Variation> getAllVariationDetails() {
        return variationRepository.findAllVariationsWithOptions();
    }

    // Method to find variations by category
    @Transactional
    public List<Variation> findVariationsByCategory(Long categoryId) {
        return variationRepository.findVariationsByCategory_Id(categoryId);
    }

    public List<Variation> test(){
        return variationRepository.findAll();
    }

    private VariationDetailDTO convertToDTO(Variation variation) {
        VariationDetailDTO dto = modelMapper.map(variation, VariationDetailDTO.class);
        if (variation.getVariationOptions() != null && !variation.getVariationOptions().isEmpty()) {
            VariationOption option = variation.getVariationOptions().iterator().next(); // Assuming you want the first one or some logic here
            dto.setVariationOptionId(option.getId());
            dto.setVariationOptionValue(option.getVariationValue());
        }
        dto.setCategoryId(variation.getCategory().getId());  // Add this line
        dto.setCategoryName(variation.getCategory().getCategoryName());  // Add this line
        return dto;
    }

}
