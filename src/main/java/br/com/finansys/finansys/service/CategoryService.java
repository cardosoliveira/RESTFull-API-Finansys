package br.com.finansys.finansys.service;

import br.com.finansys.finansys.dto.CategoryDTO;
import br.com.finansys.finansys.model.Category;

public interface CategoryService {

    Category createCategory(CategoryDTO categoryDTO);

    Category getCategory(Integer id);

    void updateCategory(Integer id, CategoryDTO categoryDTO);

    void deleteCategory(Integer id);

}
