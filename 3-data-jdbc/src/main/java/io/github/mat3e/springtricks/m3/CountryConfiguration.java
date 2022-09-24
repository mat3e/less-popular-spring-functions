package io.github.mat3e.springtricks.m3;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "springtricks.module-number", havingValue = "3")
class CountryConfiguration {

    @Bean
    CountryController onDemandController(CountryRepository repository) {
        return new CountryController(countryService(repository));
    }

    CountryService countryService() {
        return countryService(new InMemoryCountryRepository());
    }

    @Bean
    CountryService countryService(CountryRepository repository) {
        return new CountryService(
                repository,
                new CitySlugGenerator()
        );
    }
}
