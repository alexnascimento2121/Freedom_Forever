package com.br.ff.security;

import com.br.ff.model.Users;

public class CurrentUser {	
	    private String token;
	    private Users users;

	    public CurrentUser(String token, Users users) {
	        this.token = token;
	        this.users = users;
	    }

	    public String getToken() {
	        return token;
	    }

	    public Users getUsers() {
	        return users;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }

	    public void setUsers(Users users) {
	        this.users = users;
	    }
}
