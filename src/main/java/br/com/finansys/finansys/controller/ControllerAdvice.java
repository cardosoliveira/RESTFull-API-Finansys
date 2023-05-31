package br.com.finansys.finansys.controller;

import br.com.finansys.finansys.dto.GenericErrorResponseDTO;
import br.com.finansys.finansys.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
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
        return createGenericErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), errorList);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GenericErrorResponseDTO categoryError(CategoryNotFoundException e) {
        return createGenericErrorResponseDTO(HttpStatus.NOT_FOUND.value(), Arrays.asList(e.getMessage()));
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public GenericErrorResponseDTO categoryError(CategoryAlreadyExistsException e) {
        return createGenericErrorResponseDTO(HttpStatus.CONFLICT.value(), Arrays.asList(e.getMessage()));
    }

    @ExceptionHandler(CategoryInUseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericErrorResponseDTO categoryError(CategoryInUseException e) {
        return createGenericErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), Arrays.asList(e.getMessage()));
    }

    @ExceptionHandler(EntryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GenericErrorResponseDTO entryError(EntryNotFoundException e) {
        return createGenericErrorResponseDTO(HttpStatus.NOT_FOUND.value(), Arrays.asList(e.getMessage()));
    }

    @ExceptionHandler(EntryTypeNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericErrorResponseDTO entryError(EntryTypeNotValidException e) {
        return createGenericErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), Arrays.asList(e.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GenericErrorResponseDTO userError(UserNotFoundException e) {
        return createGenericErrorResponseDTO(HttpStatus.NOT_FOUND.value(), Arrays.asList(e.getMessage()));
    }

    private GenericErrorResponseDTO createGenericErrorResponseDTO(Integer status, List<String> errors) {
        return GenericErrorResponseDTO.builder()
                .timestamp(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")))
                .status(status)
                .errors(errors)
                .build();
    }
}
