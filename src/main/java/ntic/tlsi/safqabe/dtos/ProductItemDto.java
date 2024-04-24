package ntic.tlsi.safqabe.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ntic.tlsi.safqabe.enums.ProductOption;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductItemDto {
    private Long id ;
    private String name ;
    private String image ;
    @Enumerated(EnumType.STRING)
    private ProductOption optionType;
}
