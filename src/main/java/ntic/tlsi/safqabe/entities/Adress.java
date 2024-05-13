package ntic.tlsi.safqabe.entities;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Adress {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String wilaya;
    private String baladiya;
    private Long codePostal;
    private String address1;
    private String address2;

    @ManyToMany(mappedBy = "adress")
    private Set<Client> client = new HashSet<>();

    public Adress(Long id, String wilaya, String baladiya, Long codePostal, String address1, String address2) {
        this.id = id;
        this.wilaya = wilaya;
        this.baladiya = baladiya;
        this.codePostal = codePostal;
        this.address1 = address1;
        this.address2 = address2;
    }
}
