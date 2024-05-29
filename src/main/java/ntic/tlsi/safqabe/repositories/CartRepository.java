package ntic.tlsi.safqabe.repositories;

import ntic.tlsi.safqabe.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByClientId(Long clientId);
}
