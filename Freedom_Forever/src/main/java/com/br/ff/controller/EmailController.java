package com.br.ff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.ff.controller.Request.EmailRequest;
import com.br.ff.services.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {
	@Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.sendEmailWithNotification(
                emailRequest.getUserId(),
                emailRequest.getEmail(),
                emailRequest.getSubject(),
                emailRequest.getMessage()
            );
            return ResponseEntity.ok("E-mail enviado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao enviar e-mail: " + e.getMessage());
        }
    }
}
