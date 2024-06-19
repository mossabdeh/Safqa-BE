package ntic.tlsi.safqabe.config;

import ntic.tlsi.safqabe.entities.*;
import ntic.tlsi.safqabe.enums.RoleAdmin;
import ntic.tlsi.safqabe.repositories.*;
import ntic.tlsi.safqabe.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DataInitializer {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initData(AdminServiceImpl adminService, ClientServiceImpl clientService,
                               SupplierServiceImpl supplierService, AdressRepository adressRepository,
                               CategoryRepository categoryRepository, ProductService productService,
                               VariationService variationService,
                               ProductItemService productItemService) {
        return args -> {
            // Create some users
           /* adminService.saveAdmin(new Admin(null, "admin", "admin@gmail.com", "+213685894214", passwordEncoder.encode("admin"), RoleAdmin.SUPER_ADMIN));
            clientService.saveClient(new Client(null, "mossab", "mossab@gmail.com", "+213685894214", passwordEncoder.encode("mossab"), null));
            supplierService.saveSupplier(new Supplier(null, "seif", "seif@gmail.com", "+213685894214", passwordEncoder.encode("seif"), "seifShope", null));

            // Create address
            Adress adress = adressRepository.save(new Adress(null, "Constaintine", "el khroub", 25001L, "b test1 test2", ""));

            // Create category
            Category category = categoryRepository.save(new Category(null, "Cloths", null));

            // Create 20 Products using a loop
            for (int i = 1; i <= 20; i++) {
                String name = "Product " + i;
                String description = "Description for product " + i;
                String imageUrl = "https://static.zara.net/photos///2023/I/0/2/p/1063/301/401/2/w/448/1063301401_6_1_1.jpg?ts=1691411229580";
                double price = 10.00 + i;

                Product product = productService.saveProduct(new Product(null, name, description, imageUrl, price), 1L, 1L);

                // Create product items with variations
                ProductItem productItem = new ProductItem(null, "SKU" + i, "https://static.zara.net/photos///2023/I/0/2/p/0722/317/401/2/w/448/0722317401_1_1_1.jpg?ts=1684858431514", price, 100);
                productItemService.createProductItem(product.getId(), productItem, Set.of());
            }

            // Create Variations
            Variation sizeVariation = variationService.createVariation("Size");
            Variation colorVariation = variationService.createVariation("Color");

            // Create Variation Options
            VariationOption small = variationService.addVariationOption(1L, "S");
            VariationOption medium = variationService.addVariationOption(1L, "M");
            VariationOption large = variationService.addVariationOption(1L, "L");
            VariationOption xLarge = variationService.addVariationOption(1L, "XL");
            VariationOption red = variationService.addVariationOption(2L, "Red");
            VariationOption blue = variationService.addVariationOption(2L, "Blue");

            // Example product items
            ProductItem smallRedTShirt = new ProductItem(null, "SKU1", "https://www.celio.com/dw/image/v2/BGBR_PRD/on/demandware.static/-/Sites-celio-master/default/dwcbd82352/hi-res/125207-2-NEUNIR_NOIR-1.jpg?sw=376&sh=484&sm=fit&q=100", 19.99, 100);
            ProductItem mediumBlueTShirt = new ProductItem(null, "SKU2", "https://m.media-amazon.com/images/I/51zeCFrFJEL._AC_UY1000_DpWeblab_.jpg", 19.99, 50);
            ProductItem smallJeans = new ProductItem(null, "SKU3", "https://img01.ztat.net/article/spp-media-p1/262066b872f74f0aa7621b58951db518/ff6cbdedbf0f450ca759e871f6c37661.jpg?imwidth=762&filter=packshot", 19.99, 100);

            productItemService.createProductItem(1L, smallRedTShirt, Set.of(small.getId(), red.getId()));
            productItemService.createProductItem(2L, mediumBlueTShirt, Set.of(medium.getId(), blue.getId()));
            productItemService.createProductItem(3L, smallJeans, Set.of(small.getId(), blue.getId()));

            List<Variation> list = variationService.test();
           System.out.println(list.get(4).getName());
           */
            System.out.println("Data initialization completed.");
        };
    }
}
