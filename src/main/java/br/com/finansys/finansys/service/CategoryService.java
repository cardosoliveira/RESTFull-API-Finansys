package br.com.finansys.finansys.service;

import br.com.finansys.finansys.dto.CategoryDTO;
import br.com.finansys.finansys.entity.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(CategoryDTO categoryDTO);

    Category getCategory(Integer id, Integer userId);

    List<Category> getAllCategories(Integer userId);

    void updateCategory(Integer id, CategoryDTO categoryDTO, Integer userId);

    void deleteCategory(Integer id, Integer userId);

}
