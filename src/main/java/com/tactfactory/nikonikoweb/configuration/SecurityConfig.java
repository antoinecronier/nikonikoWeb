package com.tactfactory.nikonikoweb.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebMvcConfigurerAdapter  {

//	@Autowired
//	DataSource dataSource;

//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/login").setViewName("login");
//		//registry.addViewController("/access").setViewName("access");
//	}

//	@Bean
//	public ApplicationSecurity applicationSecurity() {
//		return new ApplicationSecurity();
//	}
//
//	@Order(Ordered.HIGHEST_PRECEDENCE)
//	@Configuration
//	protected static class AuthenticationSecurity extends
//			GlobalAuthenticationConfigurerAdapter {
//
//		@Autowired
//		private CustomUserDetailsService users;
//
//		@Override
//		public void init(AuthenticationManagerBuilder auth) throws Exception {
//			auth.userDetailsService(users);
//		}
//	}
//
//	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//	protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {
//
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			// @formatter:off
//			http.authorizeRequests().antMatchers("/login").permitAll().anyRequest()
//					.fullyAuthenticated().and().formLogin().loginPage("/login")
//					.failureUrl("/login?error").and().logout()
//					.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).and()
//					.exceptionHandling().accessDeniedPage("/access?error");
//			// @formatter:on
//		}
//
//	}

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth)
//			throws Exception {
//
//	    auth.inMemoryAuthentication().withUser("admin").password("admin")
//				.roles("ADMIN");
//
//		auth.jdbcAuthentication()
//				.dataSource(dataSource)
//				.usersByUsernameQuery(
//						"select login, password, enable from user where login=?")
//				.authoritiesByUsernameQuery(
//						"select login, role from user "
//						+ "INNER JOIN users_securityroles ON user.id = users_securityroles.user_id "
//						+ "INNER JOIN security_role ON users_securityroles.role_id = security_role.id  "
//						+ "where login=?")
//				.rolePrefix("ROLE_");
//	}
}
