package io.github.mat3e.springtricks.m2;

import io.github.mat3e.springtricks.config.ConditionalOnModuleNumber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnModuleNumber("2")
class CountryConfiguration {

    @Bean
    CountryController onDemandController() {
        return new CountryController(countryService(countryRepository()));
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

    @Bean
    CountryRepository countryRepository() {
        // Should be Spring Data repository
        return new InMemoryCountryRepository();
    }
}
