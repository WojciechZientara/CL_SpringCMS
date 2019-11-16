package pl.coderslab.repositories;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import pl.coderslab.entities.Author;
import pl.coderslab.entities.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Author entity) {
        entityManager.persist(entity);
    }

    public Author readById(long id) {
        Author author = entityManager.find(Author.class, id);
        Hibernate.initialize(author.getArticles());
        return author;
    }

    public void update(Author entity) {
        entityManager.merge(entity);
    }

    public void delete(Author entity) {
        entityManager.remove(entityManager.contains(entity) ?
                entity : entityManager.merge(entity)); }

    public List<Author> getAllAuthors() {
        Query query = entityManager.createQuery(
                "SELECT a FROM Author a");
        List<Author> authors = query.getResultList();
        for (Author author : authors) {
            Hibernate.initialize(author.getArticles());
        }
        return  authors;
    }

    
}
