package br.com.finansys.finansys.service.impl;

import br.com.finansys.finansys.dto.EntryDTO;
import br.com.finansys.finansys.exception.EntryNotFoundException;
import br.com.finansys.finansys.exception.EntryTypeNotValidException;
import br.com.finansys.finansys.entity.Category;
import br.com.finansys.finansys.entity.Entry;
import br.com.finansys.finansys.repository.EntryRepository;
import br.com.finansys.finansys.service.EntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EntryServiceImpl implements EntryService {

    private final EntryRepository entryRepository;

    @Override
    public Entry createEntry(EntryDTO entryDTO, Category category) {
        return entryRepository.save(Entry.builder()
                .name(entryDTO.getName())
                .description(entryDTO.getDescription())
                .type(checkIfTypeEntryIsValid(entryDTO.getType()))
                .amount(new BigDecimal(entryDTO.getAmount().replace(",", ".")))
                .date(LocalDate.parse(entryDTO.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .paid(entryDTO.getPaid())
                .category(category)
                .build());
    }

    @Override
    public Entry getEntry(Integer id) {
        return entryRepository.findById(id)
                .orElseThrow(() -> new EntryNotFoundException("No Entry with [id] = '" + id + "' was found"));
    }

    @Override
    public List<Entry> getAllEntries() {
        List<Entry> entryList = entryRepository.findAll();

        if (entryList.isEmpty()) {
            throw new EntryNotFoundException("No Entry has been registered");
        }
        return entryList;
    }

    @Override
    public void updateEntry(Integer id, EntryDTO entryDTO, Category category) {
        Entry entry = getEntry(id);
        entry.setName(entryDTO.getName());
        entry.setDescription(entryDTO.getDescription());
        entry.setType(checkIfTypeEntryIsValid(entryDTO.getType()));
        entry.setAmount(new BigDecimal(entryDTO.getAmount().replace(",", ".")));
        entry.setDate(LocalDate.parse(entryDTO.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        entry.setPaid(entryDTO.getPaid());
        entry.setCategory(category);
        entryRepository.save(entry);
    }

    @Override
    public void deleteEntry(Integer id) {
        entryRepository.delete(getEntry(id));
    }

    private String checkIfTypeEntryIsValid(String type) {
        if (type.equals("expense") || type.equals("revenue")) {
            return type.toLowerCase();
        } else {
            throw new EntryTypeNotValidException("Entry [type] has to be 'expense' or 'revenue'");
        }
    }

}
