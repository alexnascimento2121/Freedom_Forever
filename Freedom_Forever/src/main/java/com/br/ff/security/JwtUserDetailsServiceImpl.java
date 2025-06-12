package com.br.ff.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.ff.model.Users;
import com.br.ff.repository.UsersRepository;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UsersRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = repository.findByUsername(username);
		
		if(users == null) {
			throw new UsernameNotFoundException(String.format("No User found with username : ",username));
		}else {
			return JwtUserFactory.Create(users);
		}		
	}
}
