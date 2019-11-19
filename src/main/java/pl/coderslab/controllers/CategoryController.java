package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entities.Category;
import pl.coderslab.JPArepositories.ArticleRepository;
import pl.coderslab.JPArepositories.CategoryRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/categories/")
    public String displayCategories(Model model){
        List<Category> categories = categoryRepository.findAllCategories();
        Category emptyCategory = new Category();
        model.addAttribute("categories", categories);
        model.addAttribute("category", emptyCategory);
        return "displayCategories";
    }

    @PostMapping("/categories/")
    public String saveCategory(@Valid Category category, BindingResult result, Model model){
        if (!result.hasErrors()) {
            categoryRepository.save(category);
        }
        List<Category> categories = categoryRepository.findAllCategories();
        model.addAttribute("categories", categories);
        return "displayCategories";
    }

    @GetMapping("/categories/update/{categoryId}")
    public String updateCategory(@PathVariable long categoryId, Model model,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Category> categories = categoryRepository.findAllCategories();
        Category category = categoryRepository.findCategoryById(categoryId);
        model.addAttribute("categories", categories);
        model.addAttribute("category", category);
        return "displayCategories";
    }

    @PostMapping("/categories/update/{categoryId}")
    public String saveUpdateCategory(@PathVariable long categoryId, @Valid Category category, BindingResult result,
                                     Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!result.hasErrors()) {
            category.setId(categoryId);
            categoryRepository.save(category);
            response.sendRedirect(request.getContextPath() + "/categories/");
            return null;
        } else {
            List<Category> categories = categoryRepository.findAllCategories();
            model.addAttribute("categories", categories);
            return "displayCategories";
        }
    }


    @GetMapping("/categories/delete/{categoryId}")
    public void deleteCategory(@PathVariable long categoryId,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        categoryRepository.delete(categoryRepository.findCategoryById(categoryId));
        response.sendRedirect(request.getContextPath() + "/categories/");
    }

//    @Autowired
//    CategoryDao categoryDao;
//    @Autowired
//    ArticleDao articleDao;
//
//    @GetMapping("/categories/")
//    public String displayCategories(Model model){
//        List<Category> categories = categoryDao.getAllCategories();
//        Category emptyCategory = new Category();
//        model.addAttribute("categories", categories);
//        model.addAttribute("category", emptyCategory);
//        return "displayCategories";
//    }
//
//    @PostMapping("/categories/")
//    public String saveCategory(@Valid Category category, BindingResult result, Model model){
//        if (!result.hasErrors()) {
//            categoryDao.create(category);
//        }
//        List<Category> categories = categoryDao.getAllCategories();
//        model.addAttribute("categories", categories);
//        return "displayCategories";
//    }
//
//    @GetMapping("/categories/update/{categoryId}")
//    public String updateCategory(@PathVariable long categoryId, Model model,
//                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
//        List<Category> categories = categoryDao.getAllCategories();
//        Category category = categoryDao.readById(categoryId);
//        model.addAttribute("categories", categories);
//        model.addAttribute("category", category);
//        return "displayCategories";
//    }
//
//    @PostMapping("/categories/update/{categoryId}")
//    public String saveUpdateCategory(@PathVariable long categoryId, @Valid Category category, BindingResult result,
//                       Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        if (!result.hasErrors()) {
//            category.setId(categoryId);
//            categoryDao.update(category);
//            response.sendRedirect(request.getContextPath() + "/categories/");
//            return null;
//        } else {
//            List<Category> categories = categoryDao.getAllCategories();
//            model.addAttribute("categories", categories);
//            return "displayCategories";
//        }
//    }
//
//
//    @GetMapping("/categories/delete/{categoryId}")
//    public void deleteCategory(@PathVariable long categoryId,
//                                HttpServletRequest request, HttpServletResponse response) throws Exception {
//        categoryDao.delete(categoryDao.readById(categoryId));
//        response.sendRedirect(request.getContextPath() + "/categories/");
//    }
}
