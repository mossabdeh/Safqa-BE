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
                    "A plain t-shirt available in multiple sizes and colors.","https://m.media-amazon.com/images/I/51zeCFrFJEL._AC_UY1000_DpWeblab_.jpg",
                    20.00),1L,1L);
            Product jeans = productService.saveProduct(new Product(null, "Basic T-Shirt",
                    "A plain t-shirt available in multiple sizes and colors.","https://t3.ftcdn.net/jpg/04/83/25/50/360_F_483255019_m1r1ujM8EOkr8PamCHF85tQ0rHG3Fiqz.jpg",
                    50.00),1L,1L);

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
            ProductItem smallRedTShirt = new ProductItem(null, "SKU1", "https://www.celio.com/dw/image/v2/BGBR_PRD/on/demandware.static/-/Sites-celio-master/default/dwcbd82352/hi-res/125207-2-NEUNIR_NOIR-1.jpg?sw=376&sh=484&sm=fit&q=100", 19.99, 100);
            ProductItem mediumBlueTShirt = new ProductItem(null, "SKU2", "https://m.media-amazon.com/images/I/51zeCFrFJEL._AC_UY1000_DpWeblab_.jpg", 19.99, 50);
            ProductItem smallJeans = new ProductItem(null, "SKU1",
                    "https://img01.ztat.net/article/spp-media-p1/262066b872f74f0aa7621b58951db518/ff6cbdedbf0f450ca759e871f6c37661.jpg?imwidth=762&filter=packshot",
                    19.99, 100);
            productItemService.createProductItem(tShirt.getId(), smallRedTShirt, Set.of(small.getId(), red.getId()));
            productItemService.createProductItem(tShirt.getId(), mediumBlueTShirt, Set.of(medium.getId(), blue.getId()));
            productItemService.createProductItem(jeans.getId(), smallJeans, Set.of(small.getId(), blue.getId()));


            System.out.println("Data initialization completed.");
        };
    }
}
