package io.github.mat3e.springtricks.m4;

import io.github.mat3e.springtricks.config.ConditionalOnModuleNumber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnModuleNumber("4")
class CountryConfiguration {

    @Bean
    CountryController onDemandController(CountryRepository repository, CountryQueryRepository queryRepository) {
        return new CountryController(countryService(repository, queryRepository));
    }

    CountryService countryService() {
        var repo = new InMemoryCountryRepository();
        return countryService(repo, repo);
    }

    @Bean
    CountryService countryService(CountryRepository repository, CountryQueryRepository queryRepository) {
        return new CountryService(
                repository,
                new CitySlugGenerator(),
                queryRepository
        );
    }
}
