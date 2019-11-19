package pl.coderslab.JPArepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.entities.Article;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "SELECT * FROM articles WHERE draft='false' ORDER BY created DESC LIMIT ?1", nativeQuery = true)
    List<Article> findFirstXArticlesByCreated(int numberOfArticles);

    @Query(value = "SELECT DISTINCT a FROM Article a LEFT JOIN FETCH a.categories c WHERE a.draft=false AND c.id = ?1 ORDER BY a.created DESC")
    List<Article> findAllArticlesByCategory(Long category);

    @Query(value = "SELECT DISTINCT a FROM Article a LEFT JOIN FETCH a.categories WHERE a.draft=false")
    List<Article> findAllArticles();

    @Query(value = "SELECT DISTINCT a FROM Article a LEFT JOIN FETCH a.categories WHERE a.draft=false AND a.id = ?1")
    Article findArticleById(Long id);

    @Query(value = "SELECT DISTINCT a FROM Article a LEFT JOIN FETCH a.categories WHERE a.draft=true")
    List<Article> findAllDrafts();

    @Query(value = "SELECT DISTINCT a FROM Article a LEFT JOIN FETCH a.categories WHERE a.draft=true AND a.id = ?1")
    Article findDraftById(Long id);
}
