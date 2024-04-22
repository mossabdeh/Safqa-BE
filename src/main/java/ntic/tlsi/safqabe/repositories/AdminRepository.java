package ntic.tlsi.safqabe.repositories;
import ntic.tlsi.safqabe.entities.Admin;
import ntic.tlsi.safqabe.enums.RoleAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    List<Admin> findAdminByRoleAdmin(RoleAdmin roleAdmin);
}
