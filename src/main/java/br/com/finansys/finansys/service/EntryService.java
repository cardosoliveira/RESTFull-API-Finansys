package br.com.finansys.finansys.service;

import br.com.finansys.finansys.dto.EntryDTO;
import br.com.finansys.finansys.model.Category;
import br.com.finansys.finansys.model.Entry;

import java.util.List;

public interface EntryService {

    Entry createEntry(EntryDTO entryDTO, Category category);

    Entry getEntry(Integer id);

    List<Entry> getAllEntries();

    void updateEntry(Integer id, EntryDTO entryDTO, Category category);

    void deleteEntry(Integer id);

}
