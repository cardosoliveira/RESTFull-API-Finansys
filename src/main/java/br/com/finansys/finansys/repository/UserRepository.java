package br.com.finansys.finansys.repository;

import br.com.finansys.finansys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserNameAndPassword(String userName, String password);

}
