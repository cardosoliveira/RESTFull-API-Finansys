package br.com.finansys.finansys.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDTO {

    private Integer id;

    @NotNull(message = "the field [userId] is required")
    private Integer userId;

    @NotBlank(message = "the field [name] is required")
    private String name;

    private String description;

}