package ntic.tlsi.safqabe.services;

import lombok.AllArgsConstructor;
import ntic.tlsi.safqabe.entities.Admin;
import ntic.tlsi.safqabe.enums.RoleAdmin;
import ntic.tlsi.safqabe.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminServiceInterface {
    private AdminRepository adminRepository;
    @Override
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).get();
    }

    @Override
    public List<Admin> listAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public List<Admin> listAdminsByRole(RoleAdmin roleAdmin) {
        return adminRepository.findAdminByRoleAdmin(roleAdmin);
    }

    @Override //todo
    public Admin updateAdmin(Long adminId) {
        return null;
    }

    @Override  // todo
    public void deleteAdmin(Long adminId) {
       adminRepository.deleteById(adminId);
    }

}
