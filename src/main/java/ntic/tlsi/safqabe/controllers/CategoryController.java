package ntic.tlsi.safqabe.controllers;

import lombok.AllArgsConstructor;
import ntic.tlsi.safqabe.dtos.CategoryDto;
import ntic.tlsi.safqabe.entities.Category;
import ntic.tlsi.safqabe.services.CategoryServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
//@CrossOrigin("*")
public class CategoryController {
    private CategoryServiceImpl categoryService;
    private ModelMapper modelMapper;

    @GetMapping("")
    public List<Category> listCategories(){
        return categoryService.ListCategories();
    }

    @GetMapping("/dto")
    public List<CategoryDto> categoryDtoList(){
        List<Category> categories = categoryService.ListCategories();
        return categories.stream().map(this::convert2Dto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/add")
    public Category addCategory(@RequestBody CategoryDto categoryDto){
        Category category = convert2Entity(categoryDto);
        return categoryService.addCategory(category);
    }

    private CategoryDto convert2Dto (Category category){
        CategoryDto categoryDto = modelMapper.map(category ,CategoryDto.class);
        return categoryDto ;
    }

    private Category convert2Entity(CategoryDto categoryDto){
        Category category = modelMapper.map(categoryDto ,Category.class );
        return category;
    }

}
