package br.com.finansys.finansys.repository;

import br.com.finansys.finansys.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByIdAndUserId(Integer integer, Integer userId);

    List<Category> findAllByUserId(Integer userId);

}
