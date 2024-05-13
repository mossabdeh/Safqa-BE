package ntic.tlsi.safqabe.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class VariationDetailDTO {
    private Long variationId;
    private String variationName;
    private Long variationOptionId;
    private String variationOptionValue;
}
