package io.github.mat3e.springtricks.m2;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Validated
@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
class CountryController {
    private final CountryService service;

    @GetMapping("/{code}")
    ResponseEntity<CountryDto> getCountry(@Pattern(regexp = "[a-z]{2}") @PathVariable String code) {
        return ResponseEntity.of(service.findByCode(code));
    }

    @GetMapping(path = "/{code}", params = "locale")
    ResponseEntity<CountryWithCapitalDto> getLocalizedCountry(
            @Pattern(regexp = "[a-z]{2}") @PathVariable String code,
            @RequestParam Locale locale
    ) {
        return ResponseEntity.of(service.findDetailedByCodeAndLocale(code, locale));
    }

    @PostMapping
    ResponseEntity<Void> postNewCountry(@Valid @RequestBody NewCountry source) {
        service.save(source);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream()
                .collect(toMap(
                        err -> ((FieldError) err).getField(),
                        err -> Optional.ofNullable(err.getDefaultMessage()).orElse("field error"),
                        (msg1, msg2) -> Objects.equals(msg1, msg2) ? msg1 : msg1 + ", " + msg2
                ));
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    Map<String, String> handleValidationExceptions(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream()
                .collect(toMap(
                        err -> err.getPropertyPath().toString(),
                        ConstraintViolation::getMessage
                ));
    }
}
