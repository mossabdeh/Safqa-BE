package ntic.tlsi.safqabe.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemCreateDto {
    private Long id;
    private String SKU;
    private String image;
    private double price ;
    private int quantity;
    private Set<Long> variationOptionIds; // IDs of VariationOptions
}



