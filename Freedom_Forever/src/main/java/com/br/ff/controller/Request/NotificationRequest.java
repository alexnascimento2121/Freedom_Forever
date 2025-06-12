package com.br.ff.controller.Request;

import java.io.Serializable;
import java.time.LocalDateTime;

public class NotificationRequest implements Serializable {

	private static final long serialVersionUID = 5684500563833523830L;

	/**
	 * todos os dados vêm encapsulados em um único objeto, facilitando a manutenção.
	 * pode enviar os dados no formato JSON Se vários endpoints precisarem receber
	 * os mesmos parâmetros, você pode reutilizar Tbm conhecido como DTO
	 * 
	 * @author alexn
	 */

		
	private String username; // Renomeado de 'user' para 'username'

	private String message;
	private boolean read;
	private LocalDateTime timestamp;

	public NotificationRequest() {

	}

	public NotificationRequest(String username, String message) {
		this.username = username;
		this.message = message;
	}

	public String getUsername() { // Getter para o novo campo username
		return username;
	}

	public void setUsername(String username) { // Setter para o novo campo username
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
