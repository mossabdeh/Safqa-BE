package ntic.tlsi.safqabe.services;

import ntic.tlsi.safqabe.dtos.ProductCreateDto;
import ntic.tlsi.safqabe.dtos.ProductGetDto;
import ntic.tlsi.safqabe.entities.Category;
import ntic.tlsi.safqabe.entities.Product;
import ntic.tlsi.safqabe.entities.ProductItem;
import ntic.tlsi.safqabe.entities.Supplier;
import ntic.tlsi.safqabe.repositories.CategoryRepository;
import ntic.tlsi.safqabe.repositories.ProductItemRepository;
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
    private CategoryRepository categoryRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private ProductItemRepository productItemRepository;

    private final ModelMapper modelMapper = new ModelMapper();

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
        product.setApproved(false); // Set approved to false initially

        Product savedProduct = productRepository.save(product);

        // Create a default ProductItem
        ProductItem defaultItem = new ProductItem();
        defaultItem.setSKU("DEFAULT-SKU");
        defaultItem.setImage(product.getMainImage());
        defaultItem.setPrice(product.getInitialPrice());
        defaultItem.setQuantity(0); // Initial quantity can be set to 0 or any default value
        defaultItem.setProduct(savedProduct);

        productItemRepository.save(defaultItem);

        return savedProduct;
    }

    public Optional<ProductGetDto> getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        ProductGetDto productDto = modelMapper.map(product, ProductGetDto.class);
        return Optional.of(productDto);
    }

    public List<ProductGetDto> getAllApprovedProducts() {
        List<Product> products = productRepository.findAllByApproved(true);
        List<ProductGetDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            ProductGetDto productDto = modelMapper.map(product, ProductGetDto.class);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    public List<ProductGetDto> getAllUnapprovedProducts() {
        List<Product> products = productRepository.findAllByApproved(false);
        List<ProductGetDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            ProductGetDto productDto = modelMapper.map(product, ProductGetDto.class);
            productDtos.add(productDto);
        }
        return productDtos;
    }


    public List<ProductGetDto> getProductsBySupplierId(Long supplierId) {
        List<Product> products = productRepository.findBySupplierId(supplierId);
        List<ProductGetDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            ProductGetDto productDto = modelMapper.map(product, ProductGetDto.class);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    public List<ProductGetDto> getProductsByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        List<ProductGetDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            ProductGetDto productDto = modelMapper.map(product, ProductGetDto.class);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    public Product approveProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.setApproved(true);
        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductCreateDto convert2Dto(Product product) {
        return modelMapper.map(product, ProductCreateDto.class);
    }

    public Product convert2Entity(ProductCreateDto productCreateDto) {
        return modelMapper.map(productCreateDto, Product.class);
    }

    public ProductGetDto convert2GetDto(Product product) {
        return modelMapper.map(product, ProductGetDto.class);
    }
}
