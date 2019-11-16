package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entities.Category;
import pl.coderslab.repositories.ArticleDao;
import pl.coderslab.repositories.CategoryDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryDao categoryDao;
    @Autowired
    ArticleDao articleDao;

    @GetMapping("/categories/")
    public String displayCategories(Model model){
        List<Category> categories = categoryDao.getAllCategories();
        Category emptyCategory = new Category();
        model.addAttribute("categories", categories);
        model.addAttribute("category", emptyCategory);
        return "displayCategories";
    }

    @PostMapping("/categories/")
    public String saveCategory(@ModelAttribute Category category, Model model){
        categoryDao.create(category);
        List<Category> categories = categoryDao.getAllCategories();
        model.addAttribute("categories", categories);
        return "displayCategories";
    }

    @GetMapping("/categories/update/{categoryId}")
    public String updateCategory(@PathVariable long categoryId, Model model,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Category> categories = categoryDao.getAllCategories();
        Category category = categoryDao.readById(categoryId);
        model.addAttribute("categories", categories);
        model.addAttribute("category", category);
        return "displayCategories";
    }

    @PostMapping("/categories/update/{categoryId}")
    public void saveUpdateCategory(@PathVariable long categoryId, @ModelAttribute Category category, Model model,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        category.setId(categoryId);
        categoryDao.update(category);
        List<Category> categories = categoryDao.getAllCategories();
        model.addAttribute("categories", categories);
        response.sendRedirect(request.getContextPath() + "/categories/");
    }


    @GetMapping("/categories/delete/{categoryId}")
    public void deleteCategory(@PathVariable long categoryId,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        categoryDao.delete(categoryDao.readById(categoryId));
        response.sendRedirect(request.getContextPath() + "/categories/");
    }
}
