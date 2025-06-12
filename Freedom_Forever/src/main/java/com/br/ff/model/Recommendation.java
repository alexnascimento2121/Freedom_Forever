package com.br.ff.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "recommendations")
public class Recommendation {

    @Id
    private String id;

    // Referenciando usuário pelo ID, evita usar @DBRef (mais simples e performático)
    @DBRef
    private Users user;

    @Field("recommendation_text")
    private String recommendationText;

    public Recommendation() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }  

    public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getRecommendationText() {
        return recommendationText;
    }

    public void setRecommendationText(String recommendationText) {
        this.recommendationText = recommendationText;
    }
}
