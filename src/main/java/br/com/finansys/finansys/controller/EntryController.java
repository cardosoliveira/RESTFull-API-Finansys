package br.com.finansys.finansys.controller;

import br.com.finansys.finansys.dto.CategoryDTO;
import br.com.finansys.finansys.dto.EntryDTO;
import br.com.finansys.finansys.model.Category;
import br.com.finansys.finansys.model.Entry;
import br.com.finansys.finansys.service.CategoryService;
import br.com.finansys.finansys.service.EntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/entry")
@RequiredArgsConstructor
public class EntryController {

    private final EntryService entryService;

    private final CategoryService categoryService;

    @PostMapping
    @Transactional
    public EntryDTO createEntry(@RequestBody EntryDTO entryDTO) {
        Category category = categoryService.getCategory(entryDTO.getCategoryId());
        Entry entry = entryService.createEntry(entryDTO, category);
        entryDTO.setId(entry.getId());
        entryDTO.setDate(entry.getDate().toString());
        entryDTO.setCategory(CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build());
        return entryDTO;
    }

}