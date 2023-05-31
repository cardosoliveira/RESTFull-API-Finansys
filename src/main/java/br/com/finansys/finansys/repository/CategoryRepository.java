package br.com.finansys.finansys.repository;

import br.com.finansys.finansys.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
