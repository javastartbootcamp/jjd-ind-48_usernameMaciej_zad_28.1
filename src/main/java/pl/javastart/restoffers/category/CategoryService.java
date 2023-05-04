package pl.javastart.restoffers.category;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = new Category(categoryDto.getName(), categoryDto.getDescription());
        categoryRepository.save(category);
        return categoryToDto(category);
    }

    public void deleteCategoryById(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ignore) {
            // ignore
        }
    }

    public List<String> getCategoriesNames() {
        return categoryRepository.getCategoriesNames();
    }

    public List<CategoryDto> getCategories() {
        return categoryRepository.findAll().stream().map(this::categoryToDto).collect(Collectors.toList());
    }

    private CategoryDto categoryToDto(Category category) {
        return new CategoryDto(category.getId(),
                category.getName(),
                category.getDescription());
    }
}
