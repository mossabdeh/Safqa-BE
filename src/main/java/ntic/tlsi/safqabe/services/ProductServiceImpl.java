package ntic.tlsi.safqabe.services;
import lombok.AllArgsConstructor;
import ntic.tlsi.safqabe.entities.Product;
import ntic.tlsi.safqabe.entities.ProductItem;
import ntic.tlsi.safqabe.entities.Supplier;
import ntic.tlsi.safqabe.repositories.ProductItemRepository;
import ntic.tlsi.safqabe.repositories.ProductRepository;
import ntic.tlsi.safqabe.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductServiceInterface{

    private ProductRepository productRepository ;
    private ProductItemRepository productItemRepository;
    private SupplierRepository supplierRepository;
    @Override
    public Product addProduct(Product product, Set<ProductItem> items, Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId).get();
        product.setProductItems(items);
        product.setSupplier(supplier);
        items.forEach(item -> item.setProduct(product));
        return productRepository.save(product);
    }

    @Override
    public ProductItem addProductItem(Long productId, ProductItem item) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            item.setProduct(product);
            return productItemRepository.save(item);
        }
        return null;
    }

    @Override
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    @Override
    public Set<ProductItem> getProductItemsByProductId(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.map(Product::getProductItems).orElse(null);
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct, List<ProductItem> updatedItems) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.getProductItems().clear();
            updatedItems.forEach(item -> item.setProduct(existingProduct));
            existingProduct.getProductItems().addAll(updatedItems);
            return productRepository.save(existingProduct);
        }
        return null;
    }

    @Override
    public ProductItem updateProductItem(Long productId, Long itemId, ProductItem updatedItem) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            Optional<ProductItem> optionalItem = product.getProductItems().stream()
                    .filter(item -> item.getId().equals(itemId))
                    .findFirst();
            if (optionalItem.isPresent()) {
                ProductItem existingItem = optionalItem.get();
                existingItem.setName(updatedItem.getName());
                existingItem.setImage(updatedItem.getImage());
                existingItem.setOptionType(updatedItem.getOptionType());
                return productItemRepository.save(existingItem);
            }
        }
        return null;
    }


    @Override
    public void deleteProductItem(Long productId, Long itemId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            Optional<ProductItem> optionalItem = product.getProductItems().stream()
                    .filter(item -> item.getId().equals(itemId))
                    .findFirst();
            optionalItem.ifPresent(item -> {
                product.getProductItems().remove(item);
                productItemRepository.delete(item);
            });
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
