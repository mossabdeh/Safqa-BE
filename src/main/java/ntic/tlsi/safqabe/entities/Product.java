package ntic.tlsi.safqabe.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String productName;
    private String description;
    private double price ;
    private int quantity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<ProductItem> productItems = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    private Supplier supplier;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    private Category category;

    public Product(Long id, String productName, String description, double price, int quantity) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}
