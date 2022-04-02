package io.github.mat3e.springtricks.m1;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "springtricks.module-number", havingValue = "1")
class CountryConfiguration extends ResourceConfig {

    CountryConfiguration() {
        super(CountryController.class);
    }

    @Bean
    CountryController onDemandController() {
        return new CountryController();
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
