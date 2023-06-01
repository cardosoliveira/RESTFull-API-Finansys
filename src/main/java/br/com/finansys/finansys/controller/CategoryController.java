package br.com.finansys.finansys.controller;

import br.com.finansys.finansys.dto.CategoryDTO;
import br.com.finansys.finansys.entity.Category;
import br.com.finansys.finansys.service.CategoryService;
import br.com.finansys.finansys.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        userService.getUser(categoryDTO.getUserId());
        Category category = categoryService.createCategory(categoryDTO);
        categoryDTO.setId(category.getId());
        return categoryDTO;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategory(@PathVariable Integer id, @RequestParam Integer userId) {
        Category category = categoryService.getCategory(id, userId);
        return CategoryDTO.builder()
                .id(category.getId())
                .userId(category.getUserId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDTO> getAllCategories(@RequestParam Integer userId) {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        List<Category> categoryList = categoryService.getAllCategories(userId);
        categoryList.forEach(category -> {
            categoryDTOList.add(CategoryDTO.builder()
                    .id(category.getId())
                    .userId(category.getUserId())
                    .name(category.getName())
                    .description(category.getDescription())
                    .build());
        });
        return categoryDTOList;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryDTO categoryDTO) {
        categoryService.updateCategory(id, categoryDTO, categoryDTO.getUserId());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Integer id, @RequestParam Integer userId) {
        categoryService.deleteCategory(id, userId);
    }

}
