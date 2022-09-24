package io.github.mat3e.springtricks.m5.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Configuration(proxyBeanMethods = false)
@EnableJdbcRepositories("io.github.mat3e.springtricks.m5")
@ConditionalOnProperty(name = "springtricks.module-number", havingValue = "5")
class JdbcConfiguration extends AbstractJdbcConfiguration {
}
