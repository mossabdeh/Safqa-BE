package ntic.tlsi.safqabe.controllers;

import lombok.AllArgsConstructor;
import ntic.tlsi.safqabe.dtos.ProductDto;
import ntic.tlsi.safqabe.dtos.ProductItemDto;
import ntic.tlsi.safqabe.entities.Product;
import ntic.tlsi.safqabe.entities.ProductItem;
import ntic.tlsi.safqabe.services.ProductServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@CrossOrigin("*")
public class ProductController {
    private ProductServiceImpl productService ;
    private ModelMapper modelMapper;

    @PostMapping
    public Product addProduct(
            @RequestBody ProductDto productDto,
            @RequestParam Long supplierId ,
            @RequestParam Long categoryId) {
        // Convert Set<ProductItemDto> to Set<ProductItem>
        Set<ProductItem> productItems = new HashSet<>();
        for (ProductItemDto itemDto : productDto.getProductItems()) {
            ProductItem productItem = convert2Entity(itemDto);
            productItems.add(productItem);
        }
        Product converted = convert2Entity(productDto);
        return productService.addProduct(converted, productItems,supplierId ,categoryId);
    }

    @GetMapping
    public List<Product> listProducts() {
       return productService.listProducts();

    }

    @GetMapping("/dto")
    public List<ProductDto> listProductsDtos() {
        List<Product> products = productService.listProducts();
        return products.stream().map(this::convert2Dto)
                .collect(Collectors.toList());    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product productById = productService.getProductById(id);
        return  convert2Dto(productById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct, @RequestBody List<ProductItem> updatedItems) {
        Product updated = productService.updateProduct(id, updatedProduct, updatedItems);
        return updated != null
                ? new ResponseEntity<>(updated, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{productId}/items")
    public ResponseEntity<Set<ProductItem>> getProductItemsByProductId(@PathVariable Long productId) {
        Set<ProductItem> items = productService.getProductItemsByProductId(productId);
        return items != null
                ? new ResponseEntity<>(items, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{productId}/items")
    public ResponseEntity<ProductItem> addProductItem(@PathVariable Long productId, @RequestBody ProductItem item) {
        ProductItem savedItem = productService.addProductItem(productId, item);
        return savedItem != null
                ? new ResponseEntity<>(savedItem, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{productId}/items/{itemId}")
    public ResponseEntity<ProductItem> updateProductItem(@PathVariable Long productId, @PathVariable Long itemId, @RequestBody ProductItem updatedItem) {
        ProductItem updated = productService.updateProductItem(productId, itemId, updatedItem);
        return updated != null
                ? new ResponseEntity<>(updated, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{productId}/items/{itemId}")
    public ResponseEntity<Void> deleteProductItem(@PathVariable Long productId, @PathVariable Long itemId) {
        productService.deleteProductItem(productId, itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ProductDto convert2Dto (Product product){
        ProductDto productDto = modelMapper.map(product , ProductDto.class);
        return productDto ;
    }

    private Product convert2Entity(ProductDto productDto){
        Product product = modelMapper.map(productDto ,Product.class );
        return product;
    }
// ------------------- Product Item ---------------------------
    private ProductItemDto convert2Dto (ProductItem productItem){
        ProductItemDto productItemDto = modelMapper.map(productItem , ProductItemDto.class);
        return productItemDto ;
    }

    private ProductItem convert2Entity(ProductItemDto productItemDto){
        ProductItem productItem = modelMapper.map(productItemDto ,ProductItem.class );
        return productItem;
    }

}
