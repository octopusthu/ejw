package com.octopusthu.ejw.components.autoconfigure.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty("ejw.components.autoconfigure.enabled")
@Configuration
@EnableAutoConfiguration(exclude = {})
public class AutoConfigurationConfig {
}