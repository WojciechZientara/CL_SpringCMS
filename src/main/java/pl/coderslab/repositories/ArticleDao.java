package pl.coderslab.repositories;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import pl.coderslab.entities.Article;
import pl.coderslab.entities.Author;
import pl.coderslab.entities.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.awt.print.Book;
import java.util.List;

@Repository
@Transactional
public class ArticleDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Article entity) {
        entityManager.persist(entity);
    }

    public Article readById(long id) {
        Article article = entityManager.find(Article.class, id);
        Hibernate.initialize(article.getCategories());
        return article;
    }

    public void update(Article entity) {
        entityManager.merge(entity);
    }

    public void delete(Article entity) {
        entityManager.remove(entityManager.contains(entity) ?
                entity : entityManager.merge(entity)); }

    public List<Article> getLastArticles(int numberOfArticles) {
        Query query = entityManager.createQuery("SELECT a FROM Article a ORDER BY a.created DESC");
        query.setMaxResults(numberOfArticles);
        List<Article> list = query.getResultList();
        for (Article article : list) {
            Hibernate.initialize(article.getCategories());
        }
        return list;
    }

    public List<Article> getAllArticles() {
        Query query = entityManager.createQuery("SELECT a FROM Article a");
        List<Article> list = query.getResultList();
        for (Article article : list) {
            Hibernate.initialize(article.getCategories());
        }
        return list;
    }

    public List<Article> getAllArticlesInCategory(Category category) {
        long categoryId = category.getId();
        Query query = entityManager.createQuery(
                "SELECT a FROM Article a JOIN a.categories c WHERE c.id = :id ORDER BY a.created DESC");
        query.setParameter("id", categoryId);
        List<Article> list = query.getResultList();
        for (Article article : list) {
            Hibernate.initialize(article.getCategories());
        }
        return list;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
