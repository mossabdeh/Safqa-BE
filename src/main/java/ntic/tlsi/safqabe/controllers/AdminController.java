package ntic.tlsi.safqabe.controllers;

import lombok.AllArgsConstructor;
import ntic.tlsi.safqabe.entities.Admin;
import ntic.tlsi.safqabe.enums.RoleAdmin;
import ntic.tlsi.safqabe.services.AdminServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
@AllArgsConstructor
public class AdminController {
    private AdminServiceImpl adminService ;

    @GetMapping("")
    public List<Admin> listAdmins(){
        return adminService.listAdmins();
    }

    @GetMapping("/ByRole")
    public List<Admin> listAdminsByRole(@RequestParam RoleAdmin roleAdmin){
        return adminService.listAdminsByRole(roleAdmin);
    }


    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Long id){
        return adminService.getAdminById(id);
    }



}
