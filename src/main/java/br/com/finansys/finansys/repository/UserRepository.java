package br.com.finansys.finansys.repository;

import br.com.finansys.finansys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
