package com.br.ff.controller.Request;

import java.io.Serializable;

public class AnswerRequest  implements Serializable{
	/**todos os dados vêm encapsulados em um único objeto, facilitando a manutenção.
	 * pode enviar os dados no formato JSON
	 * Se vários endpoints precisarem receber os mesmos parâmetros, você pode reutilizar
	 * Tbm conhecido como DTO
	 * @author alexn
	 */
	private static final long serialVersionUID = -1883790820371662117L;
	private String questionId;
    private String userId;
    private String answerText;

    // Getters e Setters
    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }  

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
