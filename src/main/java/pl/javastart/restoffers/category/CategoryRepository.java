package pl.javastart.restoffers.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c.name from Category c")
    List<String> getCategoriesNames();

}
