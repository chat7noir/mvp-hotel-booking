package com.ib.test.hrs.mvphotelbooking.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// PERMIT ALL REQUESTS UNAUTHENTICATED!
		http.csrf().disable();
		http.authorizeRequests().anyRequest().permitAll();
	}

}
