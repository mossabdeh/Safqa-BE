package ntic.tlsi.safqabe.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ntic.tlsi.safqabe.entities.Product;

import java.util.Set;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductDto {
    private Long id ;
    private String productName;
    private String description;
    private double price ;
    private int quantity;
    private Set<ProductItemDto> productItems;




}
