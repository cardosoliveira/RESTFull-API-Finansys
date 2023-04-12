package br.com.finansys.finansys.service;

import br.com.finansys.finansys.dto.EntryDTO;
import br.com.finansys.finansys.model.Category;
import br.com.finansys.finansys.model.Entry;

public interface EntryService {

    Entry createEntry(EntryDTO entryDTO, Category category);

    Entry getEntry(Integer id);

}
