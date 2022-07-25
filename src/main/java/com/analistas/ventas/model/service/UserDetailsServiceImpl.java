package com.analistas.ventas.model.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.analistas.ventas.model.domain.Role;
import com.analistas.ventas.model.domain.User;
import com.analistas.ventas.model.repository.IUserRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
   IUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//Buscar nombre de usuario en nuestra base de datos
		User appUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User does not exist!"));
		
		Set<GrantedAuthority> grantList = new HashSet<GrantedAuthority>(); 
		for (Role role: appUser.getRoles()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getDescription());
            grantList.add(grantedAuthority);
		}
		UserDetails user = (UserDetails) new org.springframework.security.core.userdetails.User(username,appUser.getPassword(),grantList);

		return user;
	}
}
