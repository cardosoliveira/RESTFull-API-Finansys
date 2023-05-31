package br.com.finansys.finansys.controller;

import br.com.finansys.finansys.dto.LoginDTO;
import br.com.finansys.finansys.dto.UserDTO;
import br.com.finansys.finansys.model.User;
import br.com.finansys.finansys.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody @Valid UserDTO userDTO) {
        User user = userService.createUser(userDTO);
        userDTO.setId(user.getId());
        return userDTO;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUser(@PathVariable Integer id) {
        User user = userService.getUser(id);
        return UserDTO.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .password(user.getPassword())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .build();
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public Integer performLogin(@RequestBody @Valid LoginDTO loginDTO) {
        User user = userService.getUserByLogin(loginDTO);
        return user.getId();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable Integer id, @RequestBody @Valid UserDTO userDTO) {
        userService.updateUser(id, userDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

}
