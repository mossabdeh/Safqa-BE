package ntic.tlsi.safqabe.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import ntic.tlsi.safqabe.enums.ProductOption;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class ProductItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name ;
    private String image ;
    @Enumerated(EnumType.STRING)
    private ProductOption optionType;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;
}
