package ntic.tlsi.safqabe.config;

import ntic.tlsi.safqabe.entities.*;
import ntic.tlsi.safqabe.enums.ProductOption;
import ntic.tlsi.safqabe.enums.RoleAdmin;
import ntic.tlsi.safqabe.repositories.AdressRepository;
import ntic.tlsi.safqabe.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer {
    @Bean
    CommandLineRunner initData(ProductServiceImpl productService , AdminServiceImpl adminService , ClientServiceImpl clientService,
                               SupplierServiceImpl supplierService , AdressRepository adressRepository) {
        return args -> {
            // create some users
            adminService.saveAdmin(
                    new Admin(null,"admin","admin@gmail.com","+213685894214","admin", RoleAdmin.SUPER_ADMIN));
            clientService.saveClient(
                    new Client(null,"mossab","mossab@gmail.com","+213685894214","mossab",null));
            supplierService.saveSupplier(
                    new Supplier(null,"seif","seif@gmail.com","+213685894214","seif","seifShope",null));

            // create address
            Adress adress = adressRepository.save(new Adress(null, "Constaintine", "el khroub", 25001L, "b test1 test2", ""));

            // Create some Product instances

            Product product1 = Product.builder()
                    .productName("T-shirt").description("T-shirt Description ").price(19.99).quantity(200)
                    .build();

            Product product2 = Product.builder()
                    .productName("Iphone").description("Iphone Description ").price(299.99).quantity(150)
                    .build();



            ProductItem productItem1 = ProductItem.builder()
                    .name("T-shirt 1 ").image("https://contents.mediadecathlon.com/p2073340/k$5ef12366bf4dc0d69de7788ab12d11a1/t-shirt-coton-extensible-fitness-slim-beige.jpg?format=auto&quality=40&f=800x800")
                    .optionType(ProductOption.COLOR).build();
            ProductItem productItem2 = ProductItem.builder()
                    .name("T-shirt 2 ").image("https://assets.hermes.com/is/image/hermesproduct/t-shirt-broderie-h--072025HA01-worn-1-0-0-1000-1000_g.jpg")
                    .optionType(ProductOption.COLOR).build();

            Set<ProductItem> productItemsOne = new HashSet<>();
            productItemsOne.add(productItem1);
            productItemsOne.add(productItem2);


            productService.addProduct(product1,productItemsOne,1L);


            System.out.println("Data initialization completed.");
        };
    }
}
