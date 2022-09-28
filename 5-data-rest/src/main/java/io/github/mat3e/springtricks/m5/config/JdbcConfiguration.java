package io.github.mat3e.springtricks.m5.config;

import io.github.mat3e.springtricks.config.ConditionalOnModuleNumber;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@ConditionalOnModuleNumber("5")
@Configuration(proxyBeanMethods = false)
@EnableJdbcRepositories("io.github.mat3e.springtricks.m5")
class JdbcConfiguration extends AbstractJdbcConfiguration {
}
