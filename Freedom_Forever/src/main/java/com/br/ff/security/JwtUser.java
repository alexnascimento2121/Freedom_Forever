package com.br.ff.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUser implements UserDetails{

	private static final long serialVersionUID = 8536207728515615135L;
	private final String id;
	private final String username;
    private final String password;
    private final Collection <? extends GrantedAuthority> authorities;
    
    
	public JwtUser(String id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return authorities;
	}
	
	@JsonIgnore
	@Override
	public String getPassword() {		
		return password;
	}
	
	@Override
	public String getUsername() {		
		return username;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {		
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {		
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {		
		return true;
	}

	@Override
	public boolean isEnabled() {		
		return true;
	}
	
	@JsonIgnore
	public String getId() {
		return id;
	}
}
