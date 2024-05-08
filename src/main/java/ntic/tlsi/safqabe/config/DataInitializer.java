package ntic.tlsi.safqabe.config;

import ntic.tlsi.safqabe.entities.*;
import ntic.tlsi.safqabe.enums.ProductOption;
import ntic.tlsi.safqabe.enums.RoleAdmin;
import ntic.tlsi.safqabe.repositories.AdressRepository;
import ntic.tlsi.safqabe.repositories.CategoryRepository;
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
                               SupplierServiceImpl supplierService , AdressRepository adressRepository , CategoryRepository categoryRepository) {
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
            // create category
            Category category = categoryRepository.save(Category.builder().categoryName("Cloths")
                    .build());
            // Create some Product instances

            Product product1 = Product.builder()
                    .productName("T-shirt").description("T-shirt Description ").price(19.99).quantity(200)
                    .build();

            Product product2 = Product.builder()
                    .productName("jacket").description("jacket Description ").price(29.99).quantity(150)
                    .build();
            Product product3 = Product.builder()
                    .productName("Pants").description("Pants Description ").price(29.99).quantity(150)
                    .build();



            ProductItem productItem1 = ProductItem.builder()
                    .name("T-shirt 1 ").image("https://contents.mediadecathlon.com/p2073340/k$5ef12366bf4dc0d69de7788ab12d11a1/t-shirt-coton-extensible-fitness-slim-beige.jpg?format=auto&quality=40&f=800x800")
                    .optionType(ProductOption.COLOR).build();
            ProductItem productItem2 = ProductItem.builder()
                    .name("T-shirt 2 ").image("https://assets.hermes.com/is/image/hermesproduct/t-shirt-broderie-h--072025HA01-worn-1-0-0-1000-1000_g.jpg")
                    .optionType(ProductOption.COLOR).build();

            ProductItem productItem3 = ProductItem.builder()
                    .name("jacket 1 ").image("https://www.masculin.com/wp-content/uploads/sites/2/2021/10/marques-de-vetements-homme.jpg")
                    .optionType(ProductOption.SIZE).build();

            ProductItem productItem4 = ProductItem.builder()
                    .name("Pants ").image("https://i.ebayimg.com/images/g/puQAAOSwYVRhBxMV/s-l1200.webp")
                    .optionType(ProductOption.SIZE).build();

            Set<ProductItem> productItemsOne = new HashSet<>();
            productItemsOne.add(productItem1);
            productItemsOne.add(productItem2);

            Set<ProductItem> productItemstwo = new HashSet<>();
            productItemstwo.add(productItem3);

            Set<ProductItem> productItemsthree = new HashSet<>();
            productItemsthree.add(productItem4);


            productService.addProduct(product1,productItemsOne,1L , 1L);
            productService.addProduct(product2,productItemstwo,1L , 1L);
            productService.addProduct(product3,productItemsthree,1L , 1L);



            System.out.println("Data initialization completed.");
        };
    }
}
