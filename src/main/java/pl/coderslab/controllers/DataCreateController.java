package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.entities.Article;
import pl.coderslab.entities.Author;
import pl.coderslab.entities.Category;
import pl.coderslab.repositories.ArticleDao;
import pl.coderslab.repositories.AuthorDao;
import pl.coderslab.repositories.CategoryDao;

import javax.persistence.EntityManager;
import java.awt.print.Book;
import java.util.Arrays;
import java.util.Random;

@Controller
public class DataCreateController {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping("/createData")
    @ResponseBody
    public String createData(){
        
        Author author1 = new Author();
        author1.setFirstName("Jan");
        author1.setLastName("Kowalski");
        Author author2 = new Author();
        author2.setFirstName("Anna");
        author2.setLastName("Nowak");
        Author author3 = new Author();
        author3.setFirstName("Piotr");
        author3.setLastName("Malinowski");

        authorDao.create(author1);
        authorDao.create(author2);
        authorDao.create(author3);

        author1 = authorDao.readById(author1.getId());
        author2 = authorDao.readById(author2.getId());
        author3 = authorDao.readById(author3.getId());


        Category category1 = new Category();
        category1.setName("Polityka");
        Category category2 = new Category();
        category2.setName("Sport");
        Category category3 = new Category();
        category3.setName("Finanse");
        Category category4 = new Category();
        category4.setName("Technologia");
        Category category5 = new Category();
        category5.setName("Nauka");

        categoryDao.create(category1);
        categoryDao.create(category2);
        categoryDao.create(category3);
        categoryDao.create(category4);
        categoryDao.create(category5);

        category1 = categoryDao.readById(category1.getId());
        category2 = categoryDao.readById(category2.getId());
        category3 = categoryDao.readById(category3.getId());
        category4 = categoryDao.readById(category4.getId());
        category5 = categoryDao.readById(category5.getId());

        System.out.println("break");

        Random random = new Random();
        String loremIpsum = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. " +
                "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis " +
                "dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu";

        Article article1 = new Article();
        article1.setAuthor(author1);
        article1.setCategories(Arrays.asList(category1));
        article1.setTitle("Artykuł " + random.nextInt(1000) );
        article1.setContent(loremIpsum);

        Article article2 = new Article();
        article2.setAuthor(author2);
        article2.setCategories(Arrays.asList(category1, category3));
        article2.setTitle("Artykuł " + random.nextInt(1000) );
        article2.setContent(loremIpsum);

        Article article3 = new Article();
        article3.setAuthor(author3);
        article3.setCategories(Arrays.asList(category1, category5));
        article3.setTitle("Artykuł " + random.nextInt(1000) );
        article3.setContent(loremIpsum);

        Article article4 = new Article();
        article4.setAuthor(author1);
        article4.setCategories(Arrays.asList(category2));
        article4.setTitle("Artykuł " + random.nextInt(1000) );
        article4.setContent(loremIpsum);

        Article article5 = new Article();
        article5.setAuthor(author2);
        article5.setCategories(Arrays.asList(category2, category1));
        article5.setTitle("Artykuł " + random.nextInt(1000) );
        article5.setContent(loremIpsum);

        Article article6 = new Article();
        article6.setAuthor(author3);
        article6.setCategories(Arrays.asList(category2, category3));
        article6.setTitle("Artykuł " + random.nextInt(1000) );
        article6.setContent(loremIpsum);

        Article article7 = new Article();
        article7.setAuthor(author1);
        article7.setCategories(Arrays.asList(category3));
        article7.setTitle("Artykuł " + random.nextInt(1000) );
        article7.setContent(loremIpsum);

        Article article8 = new Article();
        article8.setAuthor(author2);
        article8.setCategories(Arrays.asList(category3));
        article8.setTitle("Artykuł " + random.nextInt(1000) );
        article8.setContent(loremIpsum);

        Article article9 = new Article();
        article9.setAuthor(author3);
        article9.setCategories(Arrays.asList(category4, category5));
        article9.setTitle("Artykuł " + random.nextInt(1000) );
        article9.setContent(loremIpsum);

        Article article10 = new Article();
        article10.setAuthor(author1);
        article10.setCategories(Arrays.asList(category4));
        article10.setTitle("Artykuł " + random.nextInt(1000) );
        article10.setContent(loremIpsum);

        Article article11 = new Article();
        article11.setAuthor(author2);
        article11.setCategories(Arrays.asList(category5));
        article11.setTitle("Artykuł " + random.nextInt(1000) );
        article11.setContent(loremIpsum);

        Article article12 = new Article();
        article12.setAuthor(author3);
        article12.setCategories(Arrays.asList(category2));
        article12.setTitle("Artykuł " + random.nextInt(1000) );
        article12.setContent(loremIpsum);

        articleDao.create(article1);
        articleDao.create(article2);
        articleDao.create(article3);
        articleDao.create(article4);
        articleDao.create(article5);
        articleDao.create(article6);
        articleDao.create(article7);
        articleDao.create(article8);
        articleDao.create(article9);
        articleDao.create(article10);
        articleDao.create(article11);
        articleDao.create(article12);


        return "Database filled"; }
}
