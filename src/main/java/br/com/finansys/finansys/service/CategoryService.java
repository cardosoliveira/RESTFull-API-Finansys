package br.com.finansys.finansys.service;

import br.com.finansys.finansys.dto.CategoryDTO;
import br.com.finansys.finansys.model.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(CategoryDTO categoryDTO);

    Category getCategory(Integer id);

    List<Category> getAllCategories();

    void updateCategory(Integer id, CategoryDTO categoryDTO);

    void deleteCategory(Integer id);

}
