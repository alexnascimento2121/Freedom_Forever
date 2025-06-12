package com.br.ff.controller.Response;

import com.br.ff.model.Recommendation;

public class RecommendationResponse {
	
	    private String id;
	    private String recommendationText;

	    public RecommendationResponse(Recommendation recommendation) {
	        this.id = recommendation.getId();
	        this.recommendationText = recommendation.getRecommendationText();
	    }

	    public String getId() {
	        return id;
	    }

	    public String getRecommendationText() {
	        return recommendationText;
	    }

}
