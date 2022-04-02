package io.github.mat3e.springtricks.m0;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
class CountryController {
    private final CountryService service;

    @GetMapping("/{code}")
    ResponseEntity<CountryDto> getCountry(@PathVariable String code) {
        return ResponseEntity.of(service.findByCode(code));
    }

    @GetMapping(path = "/{code}", params = "locale")
    ResponseEntity<CountryWithCapitalDto> getLocalizedCountry(@PathVariable String code, @RequestParam Locale locale) {
        return ResponseEntity.of(service.findDetailedByCodeAndLocale(code, locale));
    }
}
