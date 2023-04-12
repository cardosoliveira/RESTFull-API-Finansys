package br.com.finansys.finansys.repository;

import br.com.finansys.finansys.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Integer> {

}
