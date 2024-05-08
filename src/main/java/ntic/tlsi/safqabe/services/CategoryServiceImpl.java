package ntic.tlsi.safqabe.services;

import lombok.AllArgsConstructor;
import ntic.tlsi.safqabe.entities.Category;
import ntic.tlsi.safqabe.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryServiceInterface{
    private CategoryRepository categoryRepository ;
    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public List<Category> ListCategories() {
        return categoryRepository.findAll();
    }

    @Override //todo
    public Category updateCategory(Long id) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {
      categoryRepository.deleteById(id);
    }
}
