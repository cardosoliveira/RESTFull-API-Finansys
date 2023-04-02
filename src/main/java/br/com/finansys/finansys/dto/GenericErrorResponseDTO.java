package br.com.finansys.finansys.dto;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
public final class GenericErrorResponseDTO {

    private ZonedDateTime timestamp;

    private Integer status;

    private List<String> errors;

}