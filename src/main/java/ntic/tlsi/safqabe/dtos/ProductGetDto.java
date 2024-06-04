package ntic.tlsi.safqabe.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductGetDto {
    private Long id;
    private String productName;
    private String description;
    private String mainImage;
    public  double initialPrice;
    private Boolean approved;
    private Long categoryId; // retrieve category id
    private String categoryName; // Optional to include category name
    private Long supplierId;  // retrieve supplier id
    private String supplierShopName; // Optional to include shop name
    private String supplierFullName; // Optional to include supplier name
}
