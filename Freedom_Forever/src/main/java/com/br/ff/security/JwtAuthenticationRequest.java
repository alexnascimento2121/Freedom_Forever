package com.br.ff.security;

import java.io.Serializable;

public class JwtAuthenticationRequest implements Serializable{

	private static final long serialVersionUID = -402091872512715808L;
	
	private  String username;
    private  String password;
    
    
    
	public JwtAuthenticationRequest() {
		super();
	}

	public JwtAuthenticationRequest(String username, String password) {
		super();
		this.setUsername(username);
		this.password = password;
	}	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
