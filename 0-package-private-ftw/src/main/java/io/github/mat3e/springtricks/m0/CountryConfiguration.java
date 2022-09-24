package io.github.mat3e.springtricks.m0;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "springtricks.module-number", havingValue = "0")
class CountryConfiguration {

    @Bean
    CountryController onDemandController() {
        return new CountryController(countryFacade(countryRepository()));
    }

    // Look ma, no @Bean!
    CountryFacade countryFacade() {
        return countryFacade(new InMemoryCountryRepository());
    }

    @Bean
    CountryFacade countryFacade(CountryRepository repository) {
        return new CountryService(
                repository,
                new CitySlugGenerator()
        );
    }

    @Bean
    CountryRepository countryRepository() {
        // Temp: should be Spring Data repository
        return new InMemoryCountryRepository();
    }
}
