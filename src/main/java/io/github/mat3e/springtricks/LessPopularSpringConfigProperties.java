package io.github.mat3e.springtricks;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("springtricks")
record LessPopularSpringConfigProperties(int moduleNumber) {
}
