package br.com.finansys.finansys.repository;

import br.com.finansys.finansys.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntryRepository extends JpaRepository<Entry, Integer> {

    Optional<Entry> findByIdAndUserId(Integer integer, Integer userId);

    List<Entry> findAllByUserId(Integer userId);

}
