package io.github.mat3e.springtricks.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("springtricks")
public record LessPopularSpringConfigProperties(int moduleNumber) {
}
