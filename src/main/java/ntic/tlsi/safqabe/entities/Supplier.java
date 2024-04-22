package ntic.tlsi.safqabe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String encryptedPassword ;
    private String shopName;
    @OneToOne
    @Cascade(CascadeType.PERSIST)
    private Adress adress ;
    @OneToMany(mappedBy = "supplier")
    private Set<Product> products = new HashSet<>();

    public Supplier(Long id, String fullName, String email, String phoneNumber, String encryptedPassword, String shopName, Adress adress) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.encryptedPassword = encryptedPassword;
        this.shopName = shopName;
        this.adress = adress;
    }
}
