package br.com.finansys.finansys.service.impl;

import br.com.finansys.finansys.dto.CategoryDTO;
import br.com.finansys.finansys.model.Category;
import br.com.finansys.finansys.repository.CategoryRepository;
import br.com.finansys.finansys.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        return categoryRepository.save(Category.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .build());
    }

    @Override
    public Category getCategory(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return categoryList;
    }

    @Override
    public void updateCategory(Integer id, CategoryDTO categoryDTO) {
        categoryRepository.findById(id)
                .map(category -> {
                    category.setName(categoryDTO.getName());
                    category.setDescription(categoryDTO.getDescription());
                    categoryRepository.save(category);
                    return category;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.delete(category);
                    return category;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
