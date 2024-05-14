package ntic.tlsi.safqabe.config;

import ntic.tlsi.safqabe.entities.*;
import ntic.tlsi.safqabe.enums.RoleAdmin;
import ntic.tlsi.safqabe.repositories.*;
import ntic.tlsi.safqabe.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer {
    @Bean
    CommandLineRunner initData(AdminServiceImpl adminService, ClientServiceImpl clientService,
                               SupplierServiceImpl supplierService, AdressRepository adressRepository,
                               CategoryRepository categoryRepository,ProductService productService,
                               VariationService variationService,
                               ProductItemService productItemService) {
        return args -> {
            // Create some users
            adminService.saveAdmin(new Admin(null, "admin", "admin@gmail.com", "+213685894214", "admin", RoleAdmin.SUPER_ADMIN));
            clientService.saveClient(new Client(null, "mossab", "mossab@gmail.com", "+213685894214", "mossab", null));
            supplierService.saveSupplier(new Supplier(null, "seif", "seif@gmail.com", "+213685894214", "seif", "seifShope", null));

            // Create address
            Adress adress = adressRepository.save(new Adress(null, "Constaintine", "el khroub", 25001L, "b test1 test2", ""));

            // Create category
            Category category = categoryRepository.save(new Category(null, "Cloths", null));

            // Create Product
            Product tShirt = productService.saveProduct(new Product(null, "Basic T-Shirt",
                    "A plain t-shirt available in multiple sizes and colors.",null,
                    20.00),1L,1L);

            // Create Variations
            Variation sizeVariation = variationService.createVariation("Size");
            Variation colorVariation = variationService.createVariation("Color");

            // Create Variation Options
            VariationOption small = variationService.addVariationOption(1L , "S");
            VariationOption medium = variationService.addVariationOption(1L , "M");
            VariationOption large = variationService.addVariationOption(1L , "L");
            VariationOption xLarge = variationService.addVariationOption(1L , "XL");
            VariationOption red = variationService.addVariationOption(2L , "Red");
            VariationOption blue = variationService.addVariationOption(2L , "Blue");
            // Construct and save product items
            ProductItem smallRedTShirt = new ProductItem(null, "SKU1", "path/to/image1.jpg", 19.99, 100);
            ProductItem mediumBlueTShirt = new ProductItem(null, "SKU2", "path/to/image2.jpg", 19.99, 50);
            productItemService.createProductItem(tShirt.getId(), smallRedTShirt, Set.of(small.getId(), red.getId()));
            productItemService.createProductItem(tShirt.getId(), mediumBlueTShirt, Set.of(medium.getId(), blue.getId()));


            System.out.println("Data initialization completed.");
        };
    }
}
