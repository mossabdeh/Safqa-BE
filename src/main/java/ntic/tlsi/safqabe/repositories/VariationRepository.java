package ntic.tlsi.safqabe.repositories;

import ntic.tlsi.safqabe.entities.Variation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariationRepository extends JpaRepository<Variation, Long> {

        @Query("SELECT v FROM Variation v LEFT JOIN FETCH v.variationOptions")
        List<Variation> findAllVariationsWithOptions();


}