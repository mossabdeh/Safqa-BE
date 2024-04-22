package ntic.tlsi.safqabe;

import ntic.tlsi.safqabe.entities.Admin;
import ntic.tlsi.safqabe.entities.Adress;
import ntic.tlsi.safqabe.entities.Client;
import ntic.tlsi.safqabe.entities.Supplier;
import ntic.tlsi.safqabe.enums.RoleAdmin;
import ntic.tlsi.safqabe.repositories.AdressRepository;
import ntic.tlsi.safqabe.services.AdminServiceImpl;
import ntic.tlsi.safqabe.services.ClientServiceImpl;
import ntic.tlsi.safqabe.services.SupplierServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SafqaBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SafqaBeApplication.class, args);
    }
}
