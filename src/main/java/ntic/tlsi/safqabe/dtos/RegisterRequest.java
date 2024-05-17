package ntic.tlsi.safqabe.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ntic.tlsi.safqabe.enums.Role;
import ntic.tlsi.safqabe.enums.RoleAdmin;

@Getter
@Setter
@NoArgsConstructor // Add this annotation to generate a default constructor
public class RegisterRequest {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
    private Role role;
    private RoleAdmin adminRole; // Specific role for admin (SUPER_ADMIN, PRODUCT_MANAGER, SOUS_ADMIN)

    // Constructor for clients and suppliers
    public RegisterRequest(String fullName, String email, String phoneNumber, String password, Role role) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }

    // Constructor for admins
    public RegisterRequest(String fullName, String email, String phoneNumber, String password, Role role, RoleAdmin adminRole) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
        this.adminRole = adminRole;
    }
}
