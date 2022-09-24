package io.github.mat3e.springtricks.m4;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "springtricks.module-number", havingValue = "4")
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
