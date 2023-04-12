package br.com.finansys.finansys.service.impl;

import br.com.finansys.finansys.dto.EntryDTO;
import br.com.finansys.finansys.model.Category;
import br.com.finansys.finansys.model.Entry;
import br.com.finansys.finansys.repository.EntryRepository;
import br.com.finansys.finansys.service.EntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EntryServiceImpl implements EntryService {

    private final EntryRepository entryRepository;

    @Override
    public Entry createEntry(EntryDTO entryDTO, Category category) {
        return entryRepository.save(Entry.builder()
                .name(entryDTO.getName())
                .description(entryDTO.getDescription())
                .type(entryDTO.getType())
                .amount(new BigDecimal(entryDTO.getAmount()))
                .date(LocalDate.now())
                .paid(entryDTO.getPaid())
                .category(category)
                .build());
    }

    @Override
    public Entry getEntry(Integer id) {
        return entryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
