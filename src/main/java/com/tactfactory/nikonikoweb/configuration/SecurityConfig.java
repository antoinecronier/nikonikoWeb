package com.tactfactory.nikonikoweb.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable();// TODO check for delete

		httpSecurity.authorizeRequests().anyRequest().authenticated().and()
				.formLogin().loginPage("/login").usernameParameter("username")
				.passwordParameter("password").permitAll().and().httpBasic()
		/*
		 * .and() .authorizeRequests().anyRequest().anonymous()
		 * .antMatchers("/api","/api/**") .permitAll()
		 */
		;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin")
				.roles("ADMIN");
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery(
						"select login, password, enabled from user where login=?")
				.authoritiesByUsernameQuery(
						"select login, role from user "
						+ "INNER JOIN security_role_user ON user.id = security_role_user.users_id "
						+ "INNER JOIN security_role ON security_role_user.SecurityRole_id = security_role.id  "
						+ "where login=?")
				.rolePrefix("ROLE_");
	}
}
