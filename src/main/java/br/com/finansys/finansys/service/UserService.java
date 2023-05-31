package br.com.finansys.finansys.service;

import br.com.finansys.finansys.dto.LoginDTO;
import br.com.finansys.finansys.dto.UserDTO;
import br.com.finansys.finansys.entity.User;

public interface UserService {

    User createUser(UserDTO userDTO);

    User getUser(Integer id);

    User getUserByLogin(LoginDTO loginDTO);

    void updateUser(Integer id, UserDTO userDTO);

    void deleteUser(Integer id);

}
