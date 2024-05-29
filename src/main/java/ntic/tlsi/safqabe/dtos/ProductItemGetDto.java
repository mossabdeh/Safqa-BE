package ntic.tlsi.safqabe.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemGetDto {
    private Long id;
    private String name;
    private String sku;
    private String image;
    private Double price;
    private Integer quantity;
    private List<String> variationValues; // Simplified display of variation values
}

