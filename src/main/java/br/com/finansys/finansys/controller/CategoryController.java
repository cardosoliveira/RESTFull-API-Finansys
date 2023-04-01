package br.com.finansys.finansys.controller;

import br.com.finansys.finansys.dto.CategoryDTO;
import br.com.finansys.finansys.model.Category;
import br.com.finansys.finansys.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.createCategory(categoryDTO);
        categoryDTO.setId(category.getId());
        return categoryDTO;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategory(@PathVariable Integer id) {
        Category category = categoryService.getCategory(id);
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryDTO categoryDTO) {
        categoryService.updateCategory(id, categoryDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
    }

}
