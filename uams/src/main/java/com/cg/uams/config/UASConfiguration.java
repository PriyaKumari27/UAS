package com.cg.uams.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.cg.uams.config.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class UASConfiguration {
	@Bean
	public AuditorAware<String> auditorProvider(){
		return new AuditorAwareImpl();
	}
}