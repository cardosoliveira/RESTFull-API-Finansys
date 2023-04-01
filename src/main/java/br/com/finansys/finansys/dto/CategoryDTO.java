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

    @NotBlank(message = "the field 'name' not be empty or blank")
    private String name;

    @NotBlank(message = "the field 'description' not be empty or blank")
    private String description;

}