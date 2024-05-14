package ntic.tlsi.safqabe.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDto {
    private Long id ;
    private String productName;
    private String description;
    private String mainImage;
    public  double initialPrice;
}
