package ntic.tlsi.safqabe.repositories;

import ntic.tlsi.safqabe.entities.Variation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariationRepository extends JpaRepository<Variation, Long> {
}