package ntic.tlsi.safqabe.repositories;

import ntic.tlsi.safqabe.entities.VariationOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariationOptionRepository extends JpaRepository<VariationOption, Long> {
}