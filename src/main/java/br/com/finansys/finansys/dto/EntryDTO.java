package br.com.finansys.finansys.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class EntryDTO {

    private Integer id;

    @NotBlank(message = "the field [name] is required")
    private String name;

    @NotBlank(message = "the field [description] is required")
    private String description;

    @NotBlank(message = "the field [type] is required")
    private String type;

    @NotBlank(message = "the field [amount] is required")
    private String amount;

    @NotBlank(message = "the field [date] is required")
    private String date;

    @NotNull(message = "the field [paid] is required")
    private Boolean paid;

    @NotNull(message = "the field [categoryId] is required")
    private Integer categoryId;

    private CategoryDTO category;

}
