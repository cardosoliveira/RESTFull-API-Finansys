package br.com.finansys.finansys.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDTO {

    private Integer id;

    @NotBlank(message = "the field [name] is required")
    private String name;

    @NotBlank(message = "the field [description] is required")
    private String description;

}