package ntic.tlsi.safqabe.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Preferences {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ageGroup;

    @ElementCollection
    @CollectionTable(name = "preferences_categories", joinColumns = @JoinColumn(name = "preferences_id"))
    @Column(name = "category")
    private Set<String> categories = new HashSet<>();

    private String priceRange;
    private String shoppingFrequency;

    @ElementCollection
    @CollectionTable(name = "preferences_brands", joinColumns = @JoinColumn(name = "preferences_id"))
    @Column(name = "brand")
    private Set<String> brands = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "preferences_interests", joinColumns = @JoinColumn(name = "preferences_id"))
    @Column(name = "interest")
    private Set<String> interests = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "preferences_notifications", joinColumns = @JoinColumn(name = "preferences_id"))
    @Column(name = "notification")
    private Set<String> notifications = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;
}
