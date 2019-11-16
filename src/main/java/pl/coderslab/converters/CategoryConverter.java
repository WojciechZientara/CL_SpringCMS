package pl.coderslab.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.entities.Category;
import pl.coderslab.repositories.CategoryDao;

public class CategoryConverter implements Converter<String, Category> {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public Category convert(String s) {
        return categoryDao.readById(Long.parseLong(s));
    }

}
