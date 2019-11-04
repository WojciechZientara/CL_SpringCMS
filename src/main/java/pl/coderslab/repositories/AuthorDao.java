package pl.coderslab.repositories;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import pl.coderslab.entities.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

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

}
