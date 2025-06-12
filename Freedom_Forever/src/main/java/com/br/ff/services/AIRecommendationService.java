//package com.br.ff.services;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestTemplate;
//
//import com.br.ff.model.Answer;
//import com.br.ff.model.Recommendation;
//import com.br.ff.model.Users;
//import com.br.ff.repository.AnswerRepository;
//import com.br.ff.repository.RecommendationRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//
//@Service
//public class AIRecommendationService {
//	
//	@Autowired
//	private AnswerRepository answerRepository;
//	@Autowired
//    private RecommendationRepository recommendationRepository;
//	
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    @Value("${openai.api.key}")
//    private String openAiApiKey;
//
//    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
//
//    public Recommendation generateRecommendation(Users userId) {
//    	 List<Answer> answers = answerRepository.findByUsers(userId);
//
//    	    if (answers.isEmpty()) {
//    	        return new Recommendation();
//    	    }
//
//    	    String userResponses = formatAnswersForAI(answers);
//    	    String recommendationText = getAiRecommendationWithBackoff(userResponses);
//
//    	    // Cria uma nova recomendação associada ao usuário
//    	    Recommendation recommendation = new Recommendation();
//    	    recommendation.setUsers(userId);  // Associa o usuário à recomendação
//    	    recommendation.setRecommendationText(recommendationText);
//
//    	    // Salva a recomendação no banco de dados
//    	    return recommendationRepository.save(recommendation);
//    }
//    
//    private String getAiRecommendationWithBackoff(String userResponses) {
//        int retryCount = 0;
//        boolean success = false;
//        String recommendationText = null;
//        
//        while (retryCount < 5 && !success) {
//            try {
//                recommendationText = getAiRecommendation(userResponses);  // Método que faz a requisição real
//                success = true;  // Se a requisição for bem-sucedida, sai do loop
//            } catch (HttpClientErrorException.TooManyRequests e) {
//                retryCount++;
//                try {
//                    // Exponential backoff (2, 4, 8, 16 segundos de espera)
//                    Thread.sleep(2000 * retryCount);
//                } catch (InterruptedException ie) {
//                    Thread.currentThread().interrupt();  // Restabelece o status de interrupção
//                }
//            }
//        }
//
//        if (!success) {
//            // Se não for possível obter a recomendação após várias tentativas
//            recommendationText = "Não foi possível gerar uma recomendação no momento.";
//        }
//        
//        return recommendationText;
//    }
//
//    private String formatAnswersForAI(List<Answer> answers) {
//        StringBuilder formatted = new StringBuilder("Aqui estão as respostas do usuário para um questionário sobre dependências:\n");
//        for (Answer answer : answers) {
//            formatted.append(answer.getQuestion().getQuestionText()).append(": ").append(answer.getAnswerText()).append("\n");
//        }
//        return formatted.toString();
//    }
//    
//    
//
//    @SuppressWarnings({ "unchecked", "rawtypes" })
//	public String getAiRecommendation(String userResponses) {
//    	try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.setBearerAuth(openAiApiKey);
//
//            // Construindo JSON corretamente
//            Map<String, Object> requestBody = new HashMap<>();
//            requestBody.put("model", "gpt-3.5-turbo");
//
//            List<Map<String, String>> messages = new ArrayList<>();
//            messages.add(Map.of("role", "system", "content", "Você é um especialista em dependências e vícios, oferecendo recomendações terapêuticas."));
//            messages.add(Map.of("role", "user", "content", userResponses));
//
//            requestBody.put("messages", messages);
//            requestBody.put("temperature", 0.7);
//            requestBody.put("max_tokens", 200);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            String jsonBody = objectMapper.writeValueAsString(requestBody);
//
//            HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
//
//            ResponseEntity<Map> response = restTemplate.exchange(OPENAI_API_URL, HttpMethod.POST, entity, Map.class);
//
//            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
//            if (choices != null && !choices.isEmpty()) {
//                Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
//                return (String) message.get("content");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Erro ao processar recomendação.";
//        }
//
//        return "Não foi possível gerar uma recomendação no momento.";
//    }
//
//    public List<Recommendation> getUserRecommendations(Users userId) {
//        return recommendationRepository.findByUsers(userId);
//    }
//}
