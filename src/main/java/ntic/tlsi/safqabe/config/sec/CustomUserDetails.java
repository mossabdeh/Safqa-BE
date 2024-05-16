package ntic.tlsi.safqabe.config.sec;

import ntic.tlsi.safqabe.entities.Admin;
import ntic.tlsi.safqabe.entities.Client;
import ntic.tlsi.safqabe.entities.Supplier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public CustomUserDetails(Admin admin) {
        this.username = admin.getEmail();
        this.password = admin.getEncryptedPassword();
        this.authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(admin.getRoleAdmin().name()));
    }

    public CustomUserDetails(Client client) {
        this.username = client.getEmail();
        this.password = client.getEncryptedPassword();
        this.authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
    }

    public CustomUserDetails(Supplier supplier) {
        this.username = supplier.getEmail();
        this.password = supplier.getEncryptedPassword();
        this.authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_SUPPLIER"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
