package ntic.tlsi.safqabe.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDTO {
    private Long id;
    private String content;
    @Min(1)
    @Max(5)
    private int rating;
    private Long clientId;
    private Long productId;
}
