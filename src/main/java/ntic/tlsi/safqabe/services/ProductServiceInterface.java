package ntic.tlsi.safqabe.services;

import ntic.tlsi.safqabe.entities.Product;
import ntic.tlsi.safqabe.entities.ProductItem;

import java.util.List;
import java.util.Set;

public interface ProductServiceInterface {
    Product addProduct(Product product , Set<ProductItem> items , Long supplierId , Long categoryId);
    ProductItem addProductItem(Long productId, ProductItem item);
    List<Product> listProducts();

    Product getProductById(Long id);

    Set<ProductItem> getProductItemsByProductId(Long productId);
    Product updateProduct(Long id, Product updatedProduct, List<ProductItem> updatedItems);
    ProductItem updateProductItem(Long productId, Long itemId, ProductItem updatedItem);
    void deleteProductItem(Long productId, Long itemId);

    void deleteProduct(Long id);
}
