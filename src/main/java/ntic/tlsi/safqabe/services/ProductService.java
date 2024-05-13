package ntic.tlsi.safqabe.services;

import ntic.tlsi.safqabe.dtos.ProductCreateDto;
import ntic.tlsi.safqabe.dtos.ProductGetDto;
import ntic.tlsi.safqabe.entities.Category;
import ntic.tlsi.safqabe.entities.Product;
import ntic.tlsi.safqabe.entities.Supplier;
import ntic.tlsi.safqabe.repositories.CategoryRepository;
import ntic.tlsi.safqabe.repositories.ProductRepository;
import ntic.tlsi.safqabe.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository; // New dependency
    @Autowired
    private SupplierRepository supplierRepository; // New dependency
    private final ModelMapper modelMapper = new ModelMapper(); // Create a ModelMapper instance

    public Product saveProduct(Product product, Long categoryId, Long supplierId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        Optional<Supplier> supplierOptional = supplierRepository.findById(supplierId);

        if (categoryOptional.isEmpty() || supplierOptional.isEmpty()) {
            throw new IllegalArgumentException("Category or Supplier not found");
        }

        Category category = categoryOptional.get();
        Supplier supplier = supplierOptional.get();

        product.setCategory(category);
        product.setSupplier(supplier);

        return productRepository.save(product);
    }

    public Optional<ProductGetDto> getProductById(Long id) {
        // Use JPA projection or custom query to fetch specific fields
        Product product = productRepository.findById(id).orElseThrow();
        ProductGetDto productDto = modelMapper.map(product, ProductGetDto.class);
        return Optional.of(productDto);
    }

    public List<ProductGetDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductGetDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            ProductGetDto productDto = modelMapper.map(product, ProductGetDto.class);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    // Additional methods for specific search criteria, filtering, etc. (Optional)

   // Mapper For Creating DTO
    public ProductCreateDto convert2Dto(Product product) {
        return modelMapper.map(product, ProductCreateDto.class);
    }

    public Product convert2Entity(ProductCreateDto productCreateDto) {
        return modelMapper.map(productCreateDto, Product.class);
    }

    // Mapper For Getting DTO
    public ProductGetDto convert2GetDto(Product product) {
        return modelMapper.map(product, ProductGetDto.class);
    }
}
