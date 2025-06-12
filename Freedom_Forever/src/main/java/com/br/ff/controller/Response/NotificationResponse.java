package com.br.ff.controller.Response;

import java.time.LocalDateTime;

import com.br.ff.model.Notification;

public class NotificationResponse {
	private String id;
    private String username;
    private String message;
    private boolean read;
    private LocalDateTime timestamp;

    public NotificationResponse(Notification notification) {
        this.id = notification.getId();
        // Adicionando uma verificação de nulidade para o objeto user e para o username
        if (notification.getUser() != null) {
            this.username = notification.getUser().getUsername();
        } else {
            this.username = null; // Se o usuário for nulo, o username também será
        }
        this.message = notification.getMessage();
        this.read = notification.isRead();
        this.timestamp = notification.getTimestamp();
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
