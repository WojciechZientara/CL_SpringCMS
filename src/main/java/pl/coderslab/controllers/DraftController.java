package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entities.Article;
import pl.coderslab.entities.Author;
import pl.coderslab.entities.Category;
import pl.coderslab.JPArepositories.ArticleRepository;
import pl.coderslab.JPArepositories.AuthorRepository;
import pl.coderslab.JPArepositories.CategoryRepository;
import pl.coderslab.validationGroups.DraftGroup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class DraftController {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @ModelAttribute("authors")
    public List<Author> authors() {
        List<Author> authors = authorRepository.findAllAuthors();
        for (Author author : authors) {
            author.setLastName(author.getFirstName() + " " + author.getLastName());
        }
        return authors;
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryRepository.findAllCategories();
    }

    @GetMapping("/drafts/")
    public String displayArticles(Model model){
        List<Article> articles = articleRepository.findAllDrafts();
        Article emptyArticle = new Article();
        model.addAttribute("articles", articles);
        model.addAttribute("article", emptyArticle);
        return "displayArticles";
    }

    @PostMapping("/drafts/")
    public String saveArticle(@Validated({DraftGroup.class}) Article article, BindingResult result, Model model,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!result.hasErrors()) {
            article.setDraft(true);
            articleRepository.save(article);
            response.sendRedirect(request.getContextPath() + "/drafts/");
            return null;
        }
        List<Article> articles = articleRepository.findAllDrafts();
        model.addAttribute("articles", articles);
        return "displayArticles";
    }

    @GetMapping("/drafts/update/{articleId}")
    public String updateArticle(@PathVariable long articleId, Model model,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Article> articles = articleRepository.findAllDrafts();
        Article article = articleRepository.findDraftById(articleId);
        model.addAttribute("articles", articles);
        model.addAttribute("article", article);
        return "displayArticles";
    }

    @PostMapping("/drafts/update/{articleId}")
    public String saveUpdateArticle(@PathVariable long articleId, @Validated({DraftGroup.class}) Article article, BindingResult result,
                                    Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!result.hasErrors()) {
            article.setId(articleId);
            article.setDraft(true);
            articleRepository.save(article);
            response.sendRedirect(request.getContextPath() + "/drafts/");
            return null;
        }
        List<Article> articles = articleRepository.findAllDrafts();
        model.addAttribute("articles", articles);
        return "displayArticles";
    }


    @GetMapping("/drafts/delete/{articleId}")
    public void deleteArticle(@PathVariable long articleId,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        articleRepository.delete(articleRepository.findDraftById(articleId));
        response.sendRedirect(request.getContextPath() + "/drafts/");
    }

//    @Autowired
//    AuthorDao authorDao;
//    @Autowired
//    ArticleDao articleDao;
//    @Autowired
//    CategoryDao categoryDao;
//
//    @ModelAttribute("authors")
//    public List<Author> authors() {
//        List<Author> authors = authorDao.getAllAuthors();
//        for (Author author : authors) {
//            author.setLastName(author.getFirstName() + " " + author.getLastName());
//        }
//        return authors;
//    }
//
//    @ModelAttribute("categories")
//    public List<Category> categories() {
//        return categoryDao.getAllCategories();
//    }
//
//    @GetMapping("/drafts/")
//    public String displayArticles(Model model){
//        List<Article> articles = articleDao.getAllDrafts();
//        Article emptyArticle = new Article();
//        model.addAttribute("articles", articles);
//        model.addAttribute("article", emptyArticle);
//        return "displayArticles";
//    }
//
//    @PostMapping("/drafts/")
//    public String saveArticle(@Validated({DraftGroup.class}) Article article, BindingResult result, Model model,
//                              HttpServletRequest request, HttpServletResponse response) throws Exception {
//        if (!result.hasErrors()) {
//            article.setDraft(true);
//            articleDao.create(article);
//            response.sendRedirect(request.getContextPath() + "/drafts/");
//            return null;
//        }
//        List<Article> articles = articleDao.getAllDrafts();
//        model.addAttribute("articles", articles);
//        return "displayArticles";
//    }
//
//    @GetMapping("/drafts/update/{articleId}")
//    public String updateArticle(@PathVariable long articleId, Model model,
//                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
//        List<Article> articles = articleDao.getAllDrafts();
//        Article article = articleDao.readById(articleId);
//        model.addAttribute("articles", articles);
//        model.addAttribute("article", article);
//        return "displayArticles";
//    }
//
//    @PostMapping("/drafts/update/{articleId}")
//    public String saveUpdateArticle(@PathVariable long articleId, @Validated({DraftGroup.class}) Article article, BindingResult result,
//                      Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        if (!result.hasErrors()) {
//            article.setId(articleId);
//            article.setDraft(true);
//            articleDao.update(article);
//            response.sendRedirect(request.getContextPath() + "/drafts/");
//            return null;
//        }
//        List<Article> articles = articleDao.getAllDrafts();
//        model.addAttribute("articles", articles);
//        return "displayArticles";
//    }
//
//
//    @GetMapping("/drafts/delete/{articleId}")
//    public void deleteArticle(@PathVariable long articleId,
//                                HttpServletRequest request, HttpServletResponse response) throws Exception {
//        articleDao.delete(articleDao.readById(articleId));
//        response.sendRedirect(request.getContextPath() + "/drafts/");
//    }
}
