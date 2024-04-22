package ntic.tlsi.safqabe.repositories;

import ntic.tlsi.safqabe.entities.Product;
import ntic.tlsi.safqabe.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {

}
