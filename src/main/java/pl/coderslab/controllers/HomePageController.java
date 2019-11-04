package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entities.Article;
import pl.coderslab.entities.Category;
import pl.coderslab.repositories.ArticleDao;
import pl.coderslab.repositories.CategoryDao;

import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    ArticleDao articleDao;
    @Autowired
    CategoryDao categoryDao;

    @RequestMapping("/")
    public String homepage(Model model) {

        List<Article> lastFive = articleDao.getLastArticles(5);
        model.addAttribute("lastFive", lastFive);
        List<Category> categories = categoryDao.getAllCategories();
        model.addAttribute("categories", categories);

        return "index";
    }

    @RequestMapping("/category/{param}")
    public String categoryArticles(Model model, @PathVariable long param) {

        Category category = categoryDao.readById(param);
        model.addAttribute("category", category);

        List<Article> categoryArticles = articleDao.getAllArticlesInCategory(category);
        model.addAttribute("categoryArticles", categoryArticles);

        List<Category> categories = categoryDao.getAllCategories();
        model.addAttribute("categories", categories);

        return "categoryArticles";
    }

}
