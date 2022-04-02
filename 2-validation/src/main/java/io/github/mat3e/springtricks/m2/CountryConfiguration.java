package io.github.mat3e.springtricks.m2;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "springtricks.module-number", havingValue = "2")
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
