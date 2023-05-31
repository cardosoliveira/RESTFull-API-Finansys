package br.com.finansys.finansys.service.impl;

import br.com.finansys.finansys.dto.UserDTO;
import br.com.finansys.finansys.exception.UserNotFoundException;
import br.com.finansys.finansys.model.User;
import br.com.finansys.finansys.repository.UserRepository;
import br.com.finansys.finansys.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(UserDTO userDTO) {
        return userRepository.save(User.builder()
                .userName(userDTO.getUserName())
                .password(userDTO.getPassword())
                .fullName(userDTO.getFullName())
                .email(userDTO.getEmail())
                .build());
    }

    @Override
    public User getUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return optionalUser.get();
    }

    @Override
    public void updateUser(Integer id, UserDTO userDTO) {
        User user = getUser(id);
        userRepository.save(User.builder()
                .id(user.getId())
                .userName(userDTO.getUserName())
                .password(userDTO.getPassword())
                .fullName(userDTO.getFullName())
                .email(userDTO.getEmail())
                .build());
    }

    @Override
    public void deleteUser(Integer id) {
        User user = getUser(id);
        userRepository.delete(user);
    }

}
