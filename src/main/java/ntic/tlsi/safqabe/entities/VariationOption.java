package ntic.tlsi.safqabe.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class VariationOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String variationValue; // Renamed from 'value' to 'variationValue'

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variation_id")
    @JsonIgnore // Prevents serialization of the many-to-one side to avoid recursion
    private Variation variation;
    @ManyToMany(mappedBy = "variationOptions", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonIgnore
    private Set<ProductItem> productItems = new HashSet<>();

    public VariationOption(Long id, String variationValue, Variation variation) {
        this.id = id;
        this.variationValue = variationValue;
        this.variation = variation;
    }
}