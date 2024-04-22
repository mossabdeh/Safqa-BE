package ntic.tlsi.safqabe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ntic.tlsi.safqabe.enums.RoleAdmin;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Table(name = "admin_safqa")
public class Admin  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String encryptedPassword ;
    @Enumerated(EnumType.STRING)
    private RoleAdmin roleAdmin;

}
