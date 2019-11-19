package pl.coderslab.JPArepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entities.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "SELECT DISTINCT a FROM Author a LEFT JOIN FETCH a.articles WHERE a.id = ?1")
    Author findAuthorById(Long id);

    @Query(value = "SELECT DISTINCT a FROM Author a LEFT JOIN FETCH a.articles ORDER BY a.id")
    List<Author> findAllAuthors();

}
