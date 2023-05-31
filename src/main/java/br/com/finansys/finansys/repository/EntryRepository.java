package br.com.finansys.finansys.repository;

import br.com.finansys.finansys.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Integer> {

}
