package ntic.tlsi.safqabe.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class SimilarProductResponse {
    @JsonProperty("similar_products")
    private List<SimilarProduct> similarProducts;
}

