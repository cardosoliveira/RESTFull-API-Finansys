package br.com.finansys.finansys.service;

import br.com.finansys.finansys.dto.UserDTO;
import br.com.finansys.finansys.model.User;

public interface UserService {

    User createUser(UserDTO userDTO);

    User getUser(Integer id);

    void updateUser(Integer id, UserDTO userDTO);

    void deleteUser(Integer id);

}
