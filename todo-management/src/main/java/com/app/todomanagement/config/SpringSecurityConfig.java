package com.app.todomanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public static PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	//basic authentication
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity.csrf().disable()
		                   .authorizeHttpRequests((authorize)->{
//		                	   authorize.requestMatchers(HttpMethod.POST,"/**").hasRole("ADMIN");
//		                	   authorize.requestMatchers(HttpMethod.PUT,"/**").hasRole("ADMIN");
//		                	   authorize.requestMatchers(HttpMethod.DELETE,"/**").hasRole("ADMIN");
//		                	   authorize.requestMatchers(HttpMethod.GET,"/**").hasAnyRole("ADMIN","USER");
//		                	   authorize.requestMatchers(HttpMethod.PATCH,"/**").hasAnyRole("ADMIN","USER");
		                	   authorize.requestMatchers("/auth/**").permitAll();
		                	   authorize.anyRequest().authenticated();})
		                   .httpBasic(Customizer.withDefaults());
		return httpSecurity.build();
	}
	
	@Bean
	public AuthenticationManager  authenticationManager(AuthenticationConfiguration configuration) throws Exception
	{
		return configuration.getAuthenticationManager();
	}
	
	//in-memoryAuthentication
//	@Bean
//	public UserDetailsService userDetailsService()
//	{
//		UserDetails user=User.builder()
//				             .username("Vishal")
//				             .password(passwordEncoder().encode("Vishal@123"))
//				             .roles("USER")
//				             .build();
//		
//		UserDetails admin=User.builder()
//				              .username("admin")
//				              .password(passwordEncoder().encode("admin"))
//				              .roles("ADMIN")
//				              .build();
//		return new InMemoryUserDetailsManager(user,admin);
//	}
}
