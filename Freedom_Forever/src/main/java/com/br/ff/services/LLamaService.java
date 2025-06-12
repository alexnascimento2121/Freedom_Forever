package com.br.ff.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.stereotype.Service;

import com.br.ff.controller.Request.ChatRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LLamaService {
	private final ObjectMapper mapper = new ObjectMapper();

    public String simplePrompt(String prompt) {
        try {
            var payload = Map.of("model", "llama3", "prompt", prompt, "stream", true);
            return sendStreamingPost("http://localhost:11434/api/generate", payload);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String chatWithHistory(ChatRequest request) {
        try {
            var payload = new HashMap<String, Object>();
            payload.put("model", "llama3");
            payload.put("messages", request.getUserText());
            payload.put("stream", true);

            return sendStreamingPost("http://localhost:11434/api/chat", payload);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String embedding(String text) {
        try {
            var payload = Map.of("model", "llama3", "prompt", text);
            return sendPost("http://localhost:11434/api/embeddings", payload);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String cancel(String model) {
        try {
            var payload = Map.of("model", model);
            return sendPost("http://localhost:11434/api/generate/cancel", payload);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private String sendPost(String url, Object body) throws IOException {
        var client = HttpClients.createDefault();
        var request = new HttpPost(url);
        String json = mapper.writeValueAsString(body);
        request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));

        try (var response = client.execute(request)) {
            return new String(response.getEntity().getContent().readAllBytes());
        }
    }

    private String sendStreamingPost(String url, Object body) throws IOException {
        var client = HttpClients.createDefault();
        var request = new HttpPost(url);
        String json = mapper.writeValueAsString(body);
        request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));

        StringBuilder result = new StringBuilder();

        try (var response = client.execute(request);
             var reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    JsonNode node = mapper.readTree(line);
                    String content = node.has("response") ? node.get("response").asText() : "";
                    result.append(content);
                }
            }
        }

        return result.toString();
    }
}
