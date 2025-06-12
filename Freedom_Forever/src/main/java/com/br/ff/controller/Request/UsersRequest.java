package com.br.ff.controller.Request;

import java.io.Serializable;

import com.br.ff.model.Enum.ProfileEnum;

public class UsersRequest  implements Serializable{	
	
	/**todos os dados vêm encapsulados em um único objeto, facilitando a manutenção.
	 * pode enviar os dados no formato JSON
	 * Se vários endpoints precisarem receber os mesmos parâmetros, você pode reutilizar
	 * Tbm conhecido como DTO
	 * @author alexn
	 */
	private static final long serialVersionUID = 7035655966722260367L;
	private String id;  
    private String username;   
    private String password;    
    private ProfileEnum profile;
	public UsersRequest() {
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ProfileEnum getProfile() {
		return profile;
	}
	public void setProfile(ProfileEnum profile) {
		this.profile = profile;
	}    
}
