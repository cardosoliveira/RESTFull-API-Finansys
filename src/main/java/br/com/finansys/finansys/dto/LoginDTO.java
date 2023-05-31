package br.com.finansys.finansys.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class LoginDTO {

    private Integer id;

    @NotBlank(message = "the field [userName] is required")
    private String userName;

    @NotBlank(message = "the field [password] is required")
    private String password;

}
