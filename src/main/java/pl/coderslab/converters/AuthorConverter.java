package pl.coderslab.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.entities.Author;
import pl.coderslab.repositories.AuthorDao;

public class AuthorConverter implements Converter<String, Author> {

    @Autowired
    AuthorDao authorDao;

    @Override
    public Author convert(String s) {
        return authorDao.readById(Long.parseLong(s));
    }

}
