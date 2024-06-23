package ntic.tlsi.safqabe.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class SimilarProduct {
    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("main_image")
    private String mainImage;
}

