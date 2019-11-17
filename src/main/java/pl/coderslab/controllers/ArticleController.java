package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entities.Article;
import pl.coderslab.entities.Author;
import pl.coderslab.entities.Category;
import pl.coderslab.repositories.ArticleDao;
import pl.coderslab.repositories.AuthorDao;
import pl.coderslab.repositories.CategoryDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    AuthorDao authorDao;
    @Autowired
    ArticleDao articleDao;
    @Autowired
    CategoryDao categoryDao;

    @ModelAttribute("authors")
    public List<Author> authors() {
        List<Author> authors = authorDao.getAllAuthors();
        for (Author author : authors) {
            author.setLastName(author.getFirstName() + " " + author.getLastName());
        }
        return authors;
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryDao.getAllCategories();
    }

    @GetMapping("/articles/")
    public String displayArticles(Model model){
        List<Article> articles = articleDao.getAllArticles();
        Article emptyArticle = new Article();
        model.addAttribute("articles", articles);
        model.addAttribute("article", emptyArticle);
        return "displayArticles";
    }

    @PostMapping("/articles/")
    public void saveArticle(@ModelAttribute Article article, Model model,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        articleDao.create(article);
        List<Article> articles = articleDao.getAllArticles();
        model.addAttribute("articles", articles);
        response.sendRedirect(request.getContextPath() + "/articles/");
    }

    @GetMapping("/articles/update/{articleId}")
    public String updateArticle(@PathVariable long articleId, Model model,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Article> articles = articleDao.getAllArticles();
        Article article = articleDao.readById(articleId);
        model.addAttribute("articles", articles);
        model.addAttribute("article", article);
        return "displayArticles";
    }

    @PostMapping("/articles/update/{articleId}")
    public void saveUpdateArticle(@PathVariable long articleId, @ModelAttribute Article article, Model model,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        article.setId(articleId);
        articleDao.update(article);
        List<Article> articles = articleDao.getAllArticles();
        model.addAttribute("articles", articles);
        response.sendRedirect(request.getContextPath() + "/articles/");
    }


    @GetMapping("/articles/delete/{articleId}")
    public void deleteArticle(@PathVariable long articleId,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        articleDao.delete(articleDao.readById(articleId));
        response.sendRedirect(request.getContextPath() + "/articles/");
    }
}
