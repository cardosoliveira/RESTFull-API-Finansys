package br.com.finansys.finansys.controller;

import br.com.finansys.finansys.dto.GenericErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericErrorResponseDTO fieldValidationError(MethodArgumentNotValidException e) {
        List<String> errorList = e.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());

        return GenericErrorResponseDTO.builder()
                .timestamp(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")))
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errorList)
                .build();
    }
}
