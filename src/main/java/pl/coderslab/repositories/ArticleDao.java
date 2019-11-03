package pl.coderslab.repositories;

import org.springframework.stereotype.Repository;
import pl.coderslab.entities.Article;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.awt.print.Book;

@Repository
@Transactional
public class ArticleDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Article entity) {
        entityManager.persist(entity);
    }

    public Article readById(long id) {
        return entityManager.find(Article.class, id);
    }

    public void update(Article entity) {
        entityManager.merge(entity);
    }

    public void delete(Article entity) {
        entityManager.remove(entityManager.contains(entity) ?
                entity : entityManager.merge(entity)); }

}
