//package com.techie.microservices.order.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig{
//
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//		return httpSecurity.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
//				.oauth2ResourceServer(auth -> auth.jwt(Customizer.withDefaults())).build();
//	}
//	
//}