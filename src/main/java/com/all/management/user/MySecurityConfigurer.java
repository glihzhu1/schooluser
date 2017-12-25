package com.all.management.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Order(1)
@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
public class MySecurityConfigurer extends WebSecurityConfigurerAdapter {
	@Autowired 
	private UserDetailsService userDetailsService;
	 
	@Autowired
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.inMemoryAuthentication()
			.withUser("guolong").password("guolong").roles("ADMIN");
		
		
    	builder.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
    	//builder.userDetailsService(userDetailsService);
    }
 
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }*/
    
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			.antMatchers("/", "/login", "/resources/static/**", "/webjars/**", "/public/**", "/about").permitAll()
			.anyRequest().authenticated()
			.and()
				//.formLogin().loginPage("/login").permitAll()
				.httpBasic()
				//.failureUrl("/error")
			.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/index").deleteCookies("JSESSIONID")
				.invalidateHttpSession(true).permitAll();
			//.and()
			//	.exceptionHandling().accessDeniedPage("/403");*/
	}
    
    @Bean(name="passwordEncoder")
    public PasswordEncoder passwordencoder(){
    	return new BCryptPasswordEncoder();
    }
    
    //Spring Boot configured this already.
    /*@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
        	.antMatchers("/resources/**", "/static/**", "/public/**", "/css/**", "/js/**", "/images/**");
    }*/
    
}