//package com.tactfactory.nikonikoweb.configuration.login;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;
//import com.tactfactory.nikonikoweb.models.User;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//	private IUserCrudRepository repo;
//
//	@Autowired
//	public CustomUserDetailsService(IUserCrudRepository repo) {
//		this.repo = repo;
//		System.out.println("CustomUserDetailsService load");
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String login)
//			throws UsernameNotFoundException {
//		User user = repo.findByLogin(login);
//		System.out.println("Test log for " + user.getLogin());
//		if (user == null) {
//			return null;
//		}
//		List<GrantedAuthority> auth = AuthorityUtils
//				.commaSeparatedStringToAuthorityList("ROLE_USER");
//		if (login.equals("admin")) {
//			auth = AuthorityUtils
//					.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
//		}
//		String password = user.getPassword();
//		return new org.springframework.security.core.userdetails.User(login, password,
//				auth);
//	}
//
//}
