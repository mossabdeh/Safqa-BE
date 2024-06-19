package ntic.tlsi.safqabe.repositories;

import ntic.tlsi.safqabe.entities.Variation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariationRepository extends JpaRepository<Variation, Long> {


        List<Variation> findVariationsByCategory_Id(Long category_id);
        @Query("SELECT v FROM Variation v LEFT JOIN FETCH v.variationOptions")
        List<Variation> findAllVariationsWithOptions();


}