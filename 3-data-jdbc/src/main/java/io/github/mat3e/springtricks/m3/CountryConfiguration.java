package io.github.mat3e.springtricks.m3;

import io.github.mat3e.springtricks.config.ConditionalOnModuleNumber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnModuleNumber("3")
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
