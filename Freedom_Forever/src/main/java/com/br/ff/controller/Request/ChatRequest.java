package com.br.ff.controller.Request;

import java.io.Serializable;

public class ChatRequest implements Serializable{
	
	private static final long serialVersionUID = 6810959369154609019L;
	/**todos os dados vêm encapsulados em um único objeto, facilitando a manutenção.
	 * pode enviar os dados no formato JSON
	 * Se vários endpoints precisarem receber os mesmos parâmetros, você pode reutilizar
	 * Tbm conhecido como DTO
	 * @author alexn
	 */
	
	
	    private String userId;
	    private String userText;

	    public String getUserId() {
	        return userId;
	    }

	    public void setUserId(String userId) {
	        this.userId = userId;
	    }

	    public String getUserText() {
	        return userText;
	    }

	    public void setUserText(String userText) {
	        this.userText = userText;
	    }
}
