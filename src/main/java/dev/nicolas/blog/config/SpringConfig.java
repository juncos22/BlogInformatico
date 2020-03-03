package dev.nicolas.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.nicolas.blog.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SpringConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsServiceImpl detailsImpl;
	
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(detailsImpl).passwordEncoder(encoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/", "/info", "/registro", "/registrarse",
				"/archivos/**", "/css/**", "/images/**",
						"/login", "/posts/detalles", "/usuarios",
						"/posts/recientes", "/posts/buscar").permitAll()
				.antMatchers("/perfil", "/posts/publicar").authenticated()
			.and().formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/").failureUrl("/login?error=true")
				.usernameParameter("email").passwordParameter("password")
			.and().logout().deleteCookies("JSESSIONID").logoutUrl("/logout").logoutSuccessUrl("/");
	}
	
}
