package ntic.tlsi.safqabe.controllers;

import ntic.tlsi.safqabe.dtos.ProductCreateDto;
import ntic.tlsi.safqabe.dtos.ProductGetDto;
import ntic.tlsi.safqabe.dtos.ProductItemCreateDto;
import ntic.tlsi.safqabe.entities.Product;
import ntic.tlsi.safqabe.entities.ProductItem;
import ntic.tlsi.safqabe.services.ProductItemService;
import ntic.tlsi.safqabe.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/products") // Base path for product-related endpoints
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductItemService productItemService;



    @PostMapping // Handles POST requests for creating products
    public ResponseEntity<ProductGetDto> createProduct(@RequestBody ProductCreateDto productCreateDto,
                                                 @RequestParam(required = false) Long categoryId,
                                                 @RequestParam(required = false) Long supplierId) {
        Product product = productService.convert2Entity(productCreateDto); // Convert to Product entity
        Product savedProduct = productService.saveProduct(product, categoryId, supplierId);
        ProductGetDto getDto = productService.convert2GetDto(savedProduct); // Convert to DTO
        return ResponseEntity.ok(getDto);
    }

    @GetMapping("/{id}") // Handles GET requests for a product by ID
    public ResponseEntity<ProductGetDto> getProductById(@PathVariable Long id) {
        Optional<ProductGetDto> productOptional = productService.getProductById(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productOptional.get());
    }

    @GetMapping // Handles GET requests for all products
    public List<ProductGetDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}") // Handles DELETE requests for a product by ID
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    // ... additional methods for product items (Optional)

    @PostMapping("/products/{productId}/items")
    public ResponseEntity<ProductItem> createProductItem(@PathVariable Long productId, @RequestBody ProductItemCreateDto productItemCreateDto) {
        // Convert DTO to ProductItem entity
        ProductItem productItem = productItemService.convert2Entity(productItemCreateDto);
        // Create the product item with associated variation options and get the saved entity
        Set<Long> variationOptionIds = productItemCreateDto.getVariationOptionIds(); // Assuming your DTO includes these
        ProductItem savedItem = productItemService.createProductItem(productId, productItem, variationOptionIds);

        return ResponseEntity.ok(savedItem);
    }


    @GetMapping("/{productId}/items") // Handles GET requests for all product items of a product
    public ResponseEntity<List<ProductItem>> getProductItems(@PathVariable Long productId) {
        List<ProductItem> productItems = productItemService.getProductItemsByProductId(productId);
        return ResponseEntity.ok(productItems);
    }

    // ... additional methods for managing product items (Optional)
}

