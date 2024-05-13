package ntic.tlsi.safqabe.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String productName;
    private String description;
    private String mainImage;

    // Removed productItems as it might not be needed for retrieving product variations

    @ManyToOne(fetch = FetchType.LAZY) // Changed to LAZY as it might not always be needed immediately
    @JoinColumn(name = "supplier_id")
    @JsonIgnore
    private Supplier supplier;
    @ManyToOne(fetch = FetchType.LAZY) // Changed to LAZY as it might not always be needed immediately
    @JoinColumn(name = "category_id")

    private Category category;

    public Product(Long id, String productName, String description, String mainImage) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.mainImage = mainImage;
    }
}