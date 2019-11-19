package pl.coderslab.JPArepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entities.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.articles WHERE c.id = ?1")
    Category findCategoryById(Long id);

    @Query(value = "SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.articles ORDER BY c.id")
    List<Category> findAllCategories();

}
