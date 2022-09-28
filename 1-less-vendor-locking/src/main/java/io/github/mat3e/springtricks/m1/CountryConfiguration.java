package io.github.mat3e.springtricks.m1;

import io.github.mat3e.springtricks.config.ConditionalOnModuleNumber;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnModuleNumber("1")
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
