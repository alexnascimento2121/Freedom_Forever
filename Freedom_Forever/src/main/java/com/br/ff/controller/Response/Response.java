package com.br.ff.controller.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alexn
 * This class serve como response padrão para trocar de dados com frontend
 * @param <T>
 * @param <T>
 */
public class Response<T> {
	private T data;
	
	private List<String> errors;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getErrors() {
		if(errors == null) {
			this.errors = new ArrayList<String>();
		}
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
}
