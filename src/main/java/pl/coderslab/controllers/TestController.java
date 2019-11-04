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

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class TestController {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping("/test")
    @ResponseBody
    public String test(){






        return "test done"; }
}
