package ntic.tlsi.safqabe.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    private Set<Product> products ;

    public Category(Long id, String name) {
        this.id = id;
        categoryName = name;
    }
}
