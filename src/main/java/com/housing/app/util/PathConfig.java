package com.housing.app.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
@PropertySource("classpath:appConfig.properties")
@ConfigurationProperties(prefix = "path")

@Getter
@Setter
@NoArgsConstructor
public class PathConfig {

	private String user;
	private String listing;

}
