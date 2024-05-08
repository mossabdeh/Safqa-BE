package ntic.tlsi.safqabe.services;

import ntic.tlsi.safqabe.entities.Category;

import java.util.List;

public interface CategoryServiceInterface {
    Category addCategory(Category category);

    Category getCategoryById(Long id);

    List<Category> ListCategories();
    Category updateCategory(Long id);
    void deleteCategory(Long id);

}
