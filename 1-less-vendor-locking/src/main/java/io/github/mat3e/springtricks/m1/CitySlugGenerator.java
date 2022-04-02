package io.github.mat3e.springtricks.m1;

import java.util.Locale;
import java.util.Map;

class CitySlugGenerator {
    private final Map<String, Map<Locale, String>> codeToLocaleToSlug = Map.of(
            "WAW", Map.of(
                    Locale.forLanguageTag("pl"), "warszawa",
                    Locale.ENGLISH, "warsaw",
                    Locale.forLanguageTag("uk"), "varshava",
                    Locale.GERMAN, "warschau"
            ),
            "KIE", Map.of(
                    Locale.forLanguageTag("pl"), "kijow",
                    Locale.ENGLISH, "kyiv",
                    Locale.forLanguageTag("uk"), "kiyiv"
            )
    );

    String generateSlug(String cityCode, Locale inLocale) {
        return codeToLocaleToSlug.get(cityCode).get(inLocale);
    }
}
