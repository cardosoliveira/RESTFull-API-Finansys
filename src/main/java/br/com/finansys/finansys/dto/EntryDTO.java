package br.com.finansys.finansys.dto;

import lombok.Data;

@Data
public class EntryDTO {

    private Integer id;

    private String name;

    private String description;

    private String type;

    private String amount;

    private String date;

    private Boolean paid;

    private Integer categoryId;

    private CategoryDTO category;

}
