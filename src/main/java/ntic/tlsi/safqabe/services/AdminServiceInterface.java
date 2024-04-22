package ntic.tlsi.safqabe.services;

import ntic.tlsi.safqabe.entities.Admin;
import ntic.tlsi.safqabe.enums.RoleAdmin;

import java.util.List;

public interface AdminServiceInterface {
    Admin saveAdmin(Admin admin);

    Admin getAdminById(Long id);
    List<Admin> listAdmins();

    List<Admin> listAdminsByRole(RoleAdmin roleAdmin);

    Admin updateAdmin(Long adminId);
    void deleteAdmin(Long adminId);

}
