package ntic.tlsi.safqabe.controllers;

import lombok.AllArgsConstructor;
import ntic.tlsi.safqabe.entities.Product;
import ntic.tlsi.safqabe.entities.ProductItem;
import ntic.tlsi.safqabe.services.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductServiceImpl productService ;

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product, @RequestBody Set<ProductItem> items ,@RequestBody Long supplierId) {
        Product savedProduct = productService.addProduct(product, items,supplierId);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Product> listProducts() {
       return productService.listProducts();

    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return  productService.getProductById(id);
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



}
