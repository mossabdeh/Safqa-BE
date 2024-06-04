package ntic.tlsi.safqabe.repositories;

import ntic.tlsi.safqabe.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product , Long> {
    List<Product> findBySupplierId(Long supplierId);
    List<Product> findByCategoryId(Long categoryId);

    List<Product> findAllByApproved(boolean b);
}
