package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entities.Author;
import pl.coderslab.repositories.ArticleDao;
import pl.coderslab.repositories.AuthorDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class AuthorController {

    @Autowired
    AuthorDao authorDao;
    @Autowired
    ArticleDao articleDao;

    @GetMapping("/authors/")
    public String displayAuthors(Model model){
        List<Author> authors = authorDao.getAllAuthors();
        Author emptyAuthor = new Author();
        model.addAttribute("authors", authors);
        model.addAttribute("author", emptyAuthor);
        return "displayAuthors";
    }

    @PostMapping("/authors/")
    public String saveAuthor(@ModelAttribute Author author, Model model){
        authorDao.create(author);
        List<Author> authors = authorDao.getAllAuthors();
        model.addAttribute("authors", authors);
        return "displayAuthors";
    }

    @GetMapping("/authors/update/{authorId}")
    public String updateAuthor(@PathVariable long authorId, Model model,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Author> authors = authorDao.getAllAuthors();
        Author author = authorDao.readById(authorId);
        model.addAttribute("authors", authors);
        model.addAttribute("author", author);
        return "displayAuthors";
    }

    @PostMapping("/authors/update/{authorId}")
    public void saveUpdateAuthor(@PathVariable long authorId, @ModelAttribute Author author, Model model,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        author.setId(authorId);
        authorDao.update(author);
        List<Author> authors = authorDao.getAllAuthors();
        model.addAttribute("authors", authors);
        response.sendRedirect(request.getContextPath() + "/authors/");
    }


    @GetMapping("/authors/delete/{authorId}")
    public void deleteAuthor(@PathVariable long authorId,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorDao.delete(authorDao.readById(authorId));
        response.sendRedirect(request.getContextPath() + "/authors/");
    }
}
