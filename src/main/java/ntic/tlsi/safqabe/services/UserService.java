package ntic.tlsi.safqabe.services;

import ntic.tlsi.safqabe.dtos.RegisterRequest;
import ntic.tlsi.safqabe.entities.Admin;
import ntic.tlsi.safqabe.entities.Client;
import ntic.tlsi.safqabe.entities.Supplier;
import ntic.tlsi.safqabe.enums.Role;
import ntic.tlsi.safqabe.enums.RoleAdmin;
import ntic.tlsi.safqabe.repositories.AdminRepository;
import ntic.tlsi.safqabe.repositories.ClientRepository;
import ntic.tlsi.safqabe.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(RegisterRequest registerRequest) {
        String encryptedPassword = passwordEncoder.encode(registerRequest.getPassword());

        switch (registerRequest.getRole()) {
            case ADMIN:
                Admin admin = new Admin();
                admin.setFullName(registerRequest.getFullName());
                admin.setEmail(registerRequest.getEmail());
                admin.setPhoneNumber(registerRequest.getPhoneNumber());
                admin.setEncryptedPassword(encryptedPassword);
                admin.setRoleAdmin(registerRequest.getAdminRole());
                adminRepository.save(admin);
                break;
            case CLIENT:
                Client client = new Client();
                client.setFullName(registerRequest.getFullName());
                client.setEmail(registerRequest.getEmail());
                client.setPhoneNumber(registerRequest.getPhoneNumber());
                client.setEncryptedPassword(encryptedPassword);
                clientRepository.save(client);
                break;
            case SUPPLIER:
                Supplier supplier = new Supplier();
                supplier.setFullName(registerRequest.getFullName());
                supplier.setEmail(registerRequest.getEmail());
                supplier.setPhoneNumber(registerRequest.getPhoneNumber());
                supplier.setEncryptedPassword(encryptedPassword);
                supplierRepository.save(supplier);
                break;
            default:
                throw new IllegalArgumentException("Invalid role: " + registerRequest.getRole());
        }
    }
}
