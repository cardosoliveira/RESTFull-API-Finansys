package br.com.finansys.finansys.service.impl;

import br.com.finansys.finansys.dto.CategoryDTO;
import br.com.finansys.finansys.entity.Category;
import br.com.finansys.finansys.exception.CategoryAlreadyExistsException;
import br.com.finansys.finansys.exception.CategoryInUseException;
import br.com.finansys.finansys.exception.CategoryNotFoundException;
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
                    .userId(categoryDTO.getUserId())
                    .name(categoryDTO.getName())
                    .description(categoryDTO.getDescription())
                    .build());
        } catch (DataIntegrityViolationException e) {
            throw new CategoryAlreadyExistsException("Unable to register Category because it already exists");
        }
    }

    @Override
    public Category getCategory(Integer id, Integer userId) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("No Category with [id] = " + id + " and userId = [" + userId + "] was found"));
    }

    @Override
    public List<Category> getAllCategories(Integer userId) {
        List<Category> categoryList = categoryRepository.findAllByUserId(userId);
        if (categoryList.isEmpty()) {
            throw new CategoryNotFoundException("No Category has been registered");
        }
        return categoryList;
    }

    @Override
    public void updateCategory(Integer id, CategoryDTO categoryDTO) {
        try {
            Category category = getCategory(id, categoryDTO.getUserId());
            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
            categoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new CategoryAlreadyExistsException("Unable to register Category because it already exists");
        }
    }

    @Override
    public void deleteCategory(Integer id, Integer userId) {
        try {
            categoryRepository.delete(getCategory(id, userId));
        } catch (DataIntegrityViolationException e) {
            throw new CategoryInUseException("Could not delete Category because it is associated with some Entry");
        }
    }
}
