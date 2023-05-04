package pl.javastart.restoffers.category;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/categories")
@RestController
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("")
    CategoryDto addCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

    @DeleteMapping("/{id}")
    void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }

    @GetMapping("/names")
    List<String> getCategoriesNames() {
        return categoryService.getCategoriesNames();
    }

    @GetMapping("")
    List<CategoryDto> getCategories() {
        return categoryService.getCategories();
    }

}
