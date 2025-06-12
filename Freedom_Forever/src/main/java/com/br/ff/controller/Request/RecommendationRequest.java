package com.br.ff.controller.Request;

import java.io.Serializable;
import java.util.List;

public class RecommendationRequest implements Serializable{	
	
	    /**
	     * todos os dados vêm encapsulados em um único objeto, facilitando a manutenção.
	 * pode enviar os dados no formato JSON Se vários endpoints precisarem receber
	 * os mesmos parâmetros, você pode reutilizar Tbm conhecido como DTO
	 * 
	 */
	private static final long serialVersionUID = 8397452387522544190L;
		private String userId;
		
		private List<AnswerRequest> answers;

	    

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public List<AnswerRequest> getAnswers() {
			return answers;
		}

		public void setAnswers(List<AnswerRequest> answers) {
			this.answers = answers;
		}
}
