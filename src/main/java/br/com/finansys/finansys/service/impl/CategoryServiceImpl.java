package br.com.finansys.finansys.service.impl;

import br.com.finansys.finansys.dto.CategoryDTO;
import br.com.finansys.finansys.exception.CategoryAlreadyExistsException;
import br.com.finansys.finansys.exception.CategoryInUseException;
import br.com.finansys.finansys.exception.CategoryNotFoundException;
import br.com.finansys.finansys.entity.Category;
import br.com.finansys.finansys.repository.CategoryRepository;
import br.com.finansys.finansys.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        try {
            return categoryRepository.save(Category.builder()
                    .name(categoryDTO.getName())
                    .description(categoryDTO.getDescription())
                    .build());
        } catch (DataIntegrityViolationException e) {
            throw new CategoryAlreadyExistsException("Unable to register Category because it already exists");
        }
    }

    @Override
    public Category getCategory(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("No Category with [id] = '" + id + "' was found"));
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList.isEmpty()) {
            throw new CategoryNotFoundException("No Category has been registered");
        }
        return categoryList;
    }

    @Override
    public void updateCategory(Integer id, CategoryDTO categoryDTO) {
        try {
            Category category = getCategory(id);
            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
            categoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new CategoryAlreadyExistsException("Unable to register Category because it already exists");
        }
    }

    @Override
    public void deleteCategory(Integer id) {
        try {
            categoryRepository.delete(getCategory(id));
        } catch (DataIntegrityViolationException e) {
            throw new CategoryInUseException("Could not delete Category because it is associated with some Entry");
        }
    }

}
