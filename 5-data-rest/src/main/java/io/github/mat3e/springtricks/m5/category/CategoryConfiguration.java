package io.github.mat3e.springtricks.m5.category;

import io.github.mat3e.springtricks.config.ConditionalOnModuleNumber;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ConditionalOnModuleNumber("5")
class CategoryConfiguration implements RepositoryRestConfigurer {
    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        validatingListener.addValidator("beforeCreate", validator());
        validatingListener.addValidator("beforeSave", validator());
    }

    @Bean
    @ConditionalOnMissingBean
    Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}
