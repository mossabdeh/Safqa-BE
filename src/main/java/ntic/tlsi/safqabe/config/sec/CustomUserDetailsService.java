package ntic.tlsi.safqabe.config.sec;

import ntic.tlsi.safqabe.entities.Admin;
import ntic.tlsi.safqabe.entities.Client;
import ntic.tlsi.safqabe.entities.Supplier;
import ntic.tlsi.safqabe.repositories.AdminRepository;
import ntic.tlsi.safqabe.repositories.ClientRepository;
import ntic.tlsi.safqabe.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(username);
        if (admin != null) {
            return new CustomUserDetails(admin);
        }
        Client client = clientRepository.findByEmail(username);
        if (client != null) {
            return new CustomUserDetails(client);
        }
        Supplier supplier = supplierRepository.findByEmail(username);
        if (supplier != null) {
            return new CustomUserDetails(supplier);
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
