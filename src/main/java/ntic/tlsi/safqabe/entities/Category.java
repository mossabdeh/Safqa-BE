package ntic.tlsi.safqabe.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String categoryName;
    @OneToMany(mappedBy = "category")
    private Set<Product> products = new HashSet<>();

    public Category(Long id, String name) {
        this.id = id;
        categoryName = name;
    }
}
