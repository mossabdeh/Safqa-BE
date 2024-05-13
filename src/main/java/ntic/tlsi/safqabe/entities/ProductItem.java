package ntic.tlsi.safqabe.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProductItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String SKU;
    private String image;
    private double price ;
    private int quantity;

    // Replace attributeValues with a ManyToOne relationship to AttributeValue
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "product_configuration",
            joinColumns = @JoinColumn(name = "product_item_id"),
            inverseJoinColumns = @JoinColumn(name = "variation_option_id"))
    @JsonIgnoreProperties({"productItems"}) // Avoids back-reference serialization
    private Set<VariationOption> variationOptions;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    public ProductItem(Long id, String SKU, String image, double price, int quantity) {
        this.id = id;
        this.SKU = SKU;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }
}