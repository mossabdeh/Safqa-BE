package ntic.tlsi.safqabe.services;

import jakarta.transaction.Transactional;
import ntic.tlsi.safqabe.dtos.*;
import ntic.tlsi.safqabe.entities.Product;
import ntic.tlsi.safqabe.entities.VariationOption;
import ntic.tlsi.safqabe.entities.ProductItem;
import ntic.tlsi.safqabe.repositories.VariationOptionRepository;
import ntic.tlsi.safqabe.repositories.ProductItemRepository;
import ntic.tlsi.safqabe.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductItemService {

    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private VariationOptionRepository variationOptionRepository; // New dependency
    private ModelMapper modelMapper = new ModelMapper();
    @Transactional
    public ProductItem createProductItem(Long productId, ProductItem productItem, Set<Long> variationOptionIds) {
        // Set the product based on the provided product ID
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));
        productItem.setProduct(product);

        // Handle multiple VariationOptions
        if (variationOptionIds != null && !variationOptionIds.isEmpty()) {
            Set<VariationOption> variationOptions = variationOptionIds.stream()
                    .map(id -> variationOptionRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("VariationOption not found with ID: " + id)))
                    .collect(Collectors.toSet());
            productItem.setVariationOptions(variationOptions);
        }

        return productItemRepository.save(productItem);
    }




    public Optional<ProductItem> getProductItemById(Long id) {
        // Use the repository to fetch the ProductItem by its id
        return productItemRepository.findById(id);
    }

    public List<ProductItem> getProductItemsByProductId(Long productId) {
        // Use the repository to fetch all ProductItems associated with a specific product
        return productItemRepository.findByProductId(productId);
    }

    public void addProductValue(Long productItemId, VariationOption variationOption) {
        ProductItem productItem = getProductItemById(productItemId).orElseThrow();
        // productItem.setVariationOption(variationOption); // Assuming a Set for AttributeValues
       // variationOption.setProductItem(productItem);
        variationOptionRepository.save(variationOption);
        productItemRepository.save(productItem); // Update product item with new attribute value
    }

    // Method to create a new ProductItem from DTO and save it
    public ProductItem convert2Entity(ProductItemCreateDto productItemCreateDto) {
        return modelMapper.map(productItemCreateDto, ProductItem.class);
    }   
    // Method to convert ProductItemPostDto to ProductItem entity
    public ProductItemCreateDto convert2Dto(ProductItem productItem) {
        return modelMapper.map(productItem, ProductItemCreateDto.class);
    }

}
