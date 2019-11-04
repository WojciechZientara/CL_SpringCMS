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
import java.util.List;

@Repository
@Transactional
public class CategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Category entity) {
        entityManager.persist(entity);
    }

//    public Category readById(long id) {
//        return entityManager.find(Category.class, id);
//    }

    public Category readById(long id) {
        Category category = entityManager.find(Category.class, id);
        Hibernate.initialize(category.getArticles());
        return category;
    }

    public void update(Category entity) {
        entityManager.merge(entity);
    }

    public void delete(Category entity) {
        entityManager.remove(entityManager.contains(entity) ?
                entity : entityManager.merge(entity)); }

    public List<Category> getAllCategories() {
        Query query = entityManager.createQuery(
                "SELECT c FROM Category c");
        return query.getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
