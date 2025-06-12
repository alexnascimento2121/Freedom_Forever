package com.br.ff.controller.Request;

import java.io.Serializable;

public class EmailRequest implements Serializable{
	/**todos os dados vêm encapsulados em um único objeto, facilitando a manutenção.
	 * pode enviar os dados no formato JSON Se vários endpoints precisarem receber
	 * os mesmos parâmetros, você pode reutilizar Tbm conhecido como DTO
	 * 
	 */
	private static final long serialVersionUID = 8297984827310446199L;
	private String userId;
    private String email;
    private String subject;
    private String message;
    
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}    
}
