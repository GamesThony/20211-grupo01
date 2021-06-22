package com.fatec.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override 
	protected void configure(HttpSecurity http) throws Exception { 
		http.authorizeRequests()
			.antMatchers("/").hasAnyRole("ADM","ATD")
			.anyRequest().authenticated().and()
			.formLogin().loginPage("/login").permitAll().and()               
			.logout().logoutSuccessUrl("/login?logout").permitAll().and()
			.csrf().disable();    
	}
	
	@Override 
	public void configure(AuthenticationManagerBuilder auth) throws Exception { 
		auth.inMemoryAuthentication() 
			.withUser("admin").password(pc().encode("123")).roles("ADM").and()                                           
			.withUser("employee").password(pc().encode("123")).roles("ATD"); 
	} 
	
	@Bean 
	public BCryptPasswordEncoder pc() { return new BCryptPasswordEncoder(); } 
	
	@Override 
	public void configure(WebSecurity web) throws Exception { 
		web.ignoring().antMatchers("/static/**", "/css/**", "/js/**", "/images/**", "/h2-console/**"); 
	} 
}