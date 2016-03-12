package br.com.michael.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	private UserDetailsService users;

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/produto/cadastro").hasRole("ADMIN")
		.antMatchers("/carrinho/**").permitAll()
		.antMatchers(HttpMethod.POST,"/produto").hasRole("ADMIN")
		.antMatchers("/produto/**").permitAll()
		.antMatchers("/pagamento/**").hasAnyRole("ADMIN", "COMPRADOR")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.usernameParameter("loginUser").passwordParameter("senhaUser")
		.defaultSuccessUrl("/produto/listagem")
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(users).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	
}
