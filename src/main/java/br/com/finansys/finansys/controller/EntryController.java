package br.com.finansys.finansys.controller;

import br.com.finansys.finansys.dto.CategoryDTO;
import br.com.finansys.finansys.dto.EntryDTO;
import br.com.finansys.finansys.model.Category;
import br.com.finansys.finansys.model.Entry;
import br.com.finansys.finansys.service.CategoryService;
import br.com.finansys.finansys.service.EntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/entry")
@RequiredArgsConstructor
public class EntryController {

    private final EntryService entryService;

    private final CategoryService categoryService;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
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

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntryDTO getEntry(@PathVariable Integer id) {
        Entry entry = entryService.getEntry(id);
        return EntryDTO.builder()
                .id(entry.getId())
                .name(entry.getName())
                .description(entry.getDescription())
                .type(entry.getType())
                .amount(entry.getAmount().toString())
                .date(entry.getDate().toString())
                .paid(entry.getPaid())
                .categoryId(entry.getCategory().getId())
                .category(CategoryDTO.builder()
                        .id(entry.getCategory().getId())
                        .name(entry.getCategory().getName())
                        .description(entry.getCategory().getDescription())
                        .build())
                .build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EntryDTO> getAllEntries() {
        List<Entry> entryList = entryService.getAllEntries();
        List<EntryDTO> entryDTOList = new ArrayList<>();

        entryList.forEach(entry -> {
            EntryDTO entryDTO = EntryDTO.builder()
                    .id(entry.getId())
                    .name(entry.getName())
                    .description(entry.getDescription())
                    .type(entry.getType())
                    .amount(entry.getAmount().toString())
                    .date(entry.getDate().toString())
                    .paid(entry.getPaid())
                    .categoryId(entry.getCategory().getId())
                    .category(CategoryDTO.builder()
                            .id(entry.getCategory().getId())
                            .name(entry.getCategory().getName())
                            .description(entry.getCategory().getDescription())
                            .build())
                    .build();
            entryDTOList.add(entryDTO);
        });
        return entryDTOList;
    }

    @Transactional
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEntry(@PathVariable Integer id, @RequestBody EntryDTO entryDTO) {
        Category category = categoryService.getCategory(entryDTO.getCategoryId());
        entryService.updateEntry(id, entryDTO, category);
    }

}
