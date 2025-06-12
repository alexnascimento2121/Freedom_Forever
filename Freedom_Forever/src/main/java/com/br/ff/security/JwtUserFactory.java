package com.br.ff.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.br.ff.model.Users;
import com.br.ff.model.Enum.ProfileEnum;

public class JwtUserFactory {
private JwtUserFactory() {
		
	}
	
	// gera jwtuser a partir dos dados do usuario
	public static JwtUser Create(Users users) {
		return new JwtUser(users.getId(), users.getUsername(), users.getPassword(), mapToGrantedAuthorities(users.getProfile()));
	}
	
	//metodo que converte perfil do usuario para perfil do security
	private static List<GrantedAuthority> mapToGrantedAuthorities(ProfileEnum profile) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(profile.toString()));
		return authorities;
	}

}
