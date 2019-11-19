package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entities.Author;
import pl.coderslab.JPArepositories.ArticleRepository;
import pl.coderslab.JPArepositories.AuthorRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/authors/")
    public String displayAuthors(Model model){
        List<Author> authors = authorRepository.findAllAuthors();
        Author emptyAuthor = new Author();
        model.addAttribute("authors", authors);
        model.addAttribute("author", emptyAuthor);
        return "displayAuthors";
    }

    @PostMapping("/authors/")
    public String saveAuthor(@Valid Author author, BindingResult result, Model model){
        if (!result.hasErrors()) {
            authorRepository.save(author);
        }
        List<Author> authors = authorRepository.findAllAuthors();
        model.addAttribute("authors", authors);
        return "displayAuthors";
    }

    @GetMapping("/authors/update/{authorId}")
    public String updateAuthor(@PathVariable long authorId, Model model,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Author> authors = authorRepository.findAllAuthors();
        Author author = authorRepository.findAuthorById(authorId);
        model.addAttribute("authors", authors);
        model.addAttribute("author", author);
        return "displayAuthors";
    }

    @PostMapping("/authors/update/{authorId}")
    public String saveUpdateAuthor(@PathVariable long authorId, @Valid Author author, BindingResult result,
                                   Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!result.hasErrors()) {
            author.setId(authorId);
            authorRepository.save(author);
            response.sendRedirect(request.getContextPath() + "/authors/");
            return null;
        }
        List<Author> authors = authorRepository.findAllAuthors();
        model.addAttribute("authors", authors);
        return "displayAuthors";
    }


    @GetMapping("/authors/delete/{authorId}")
    public void deleteAuthor(@PathVariable long authorId,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorRepository.delete(authorRepository.findAuthorById(authorId));
        response.sendRedirect(request.getContextPath() + "/authors/");
    }

//    @Autowired
//    AuthorDao authorDao;
//    @Autowired
//    ArticleDao articleDao;
//
//    @GetMapping("/authors/")
//    public String displayAuthors(Model model){
//        List<Author> authors = authorDao.getAllAuthors();
//        Author emptyAuthor = new Author();
//        model.addAttribute("authors", authors);
//        model.addAttribute("author", emptyAuthor);
//        return "displayAuthors";
//    }
//
//    @PostMapping("/authors/")
//    public String saveAuthor(@Valid Author author, BindingResult result, Model model){
//        if (!result.hasErrors()) {
//            authorDao.create(author);
//        }
//        List<Author> authors = authorDao.getAllAuthors();
//        model.addAttribute("authors", authors);
//        return "displayAuthors";
//    }
//
//    @GetMapping("/authors/update/{authorId}")
//    public String updateAuthor(@PathVariable long authorId, Model model,
//                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
//        List<Author> authors = authorDao.getAllAuthors();
//        Author author = authorDao.readById(authorId);
//        model.addAttribute("authors", authors);
//        model.addAttribute("author", author);
//        return "displayAuthors";
//    }
//
//    @PostMapping("/authors/update/{authorId}")
//    public String saveUpdateAuthor(@PathVariable long authorId, @Valid Author author, BindingResult result,
//                         Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        if (!result.hasErrors()) {
//            author.setId(authorId);
//            authorDao.update(author);
//            response.sendRedirect(request.getContextPath() + "/authors/");
//            return null;
//        }
//        List<Author> authors = authorDao.getAllAuthors();
//        model.addAttribute("authors", authors);
//        return "displayAuthors";
//    }
//
//
//    @GetMapping("/authors/delete/{authorId}")
//    public void deleteAuthor(@PathVariable long authorId,
//                                HttpServletRequest request, HttpServletResponse response) throws Exception {
//        authorDao.delete(authorDao.readById(authorId));
//        response.sendRedirect(request.getContextPath() + "/authors/");
//    }
}
