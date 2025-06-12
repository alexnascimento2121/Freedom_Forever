package com.br.ff.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.ff.controller.Request.NotificationRequest;
import com.br.ff.controller.Response.NotificationResponse;
import com.br.ff.controller.Response.Response;
import com.br.ff.model.Notification;
import com.br.ff.services.EmailService;
import com.br.ff.services.NotificationService;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private EmailService emailService;
    @GetMapping("/unread/username/{username}")
    public ResponseEntity<Response<List<NotificationResponse>>> getUnreadNotificationsByUsername(@PathVariable String username) {
        Response<List<NotificationResponse>> response = new Response<>();
        
        try {
            List<Notification> unreadNotifications = notificationService.getUnreadNotifications(username); // Chama o método do serviço com username

            if (unreadNotifications.isEmpty()) {
                response.getErrors().add("Nenhuma notificação não lida encontrada para o usuário: " + username);
                return ResponseEntity.ok(response); // Retorna 200 OK com lista vazia e mensagem de erro
            }

            // Convertendo Notification para NotificationResponse
            List<NotificationResponse> dtoList = unreadNotifications.stream()
                    .map(NotificationResponse::new)
                    .collect(Collectors.toList());

            response.setData(dtoList);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) { // Captura a exceção lançada pelo serviço se o usuário não for encontrado
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }



    @PostMapping("/create" )
    public ResponseEntity<Response<NotificationResponse>> createNotification(@RequestBody NotificationRequest requestDTO) {
        Response<NotificationResponse> response = new Response<>();

        try {
            // Passamos o username e a mensagem para o serviço
            Notification notification = notificationService.createNotification(
                requestDTO.getUsername(), 
                requestDTO.getMessage()
            );

            response.setData(new NotificationResponse(notification));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/markAsRead/{notificationId}")
    public ResponseEntity<Response<NotificationResponse>> markAsRead(@PathVariable String notificationId) {
        Response<NotificationResponse> response = new Response<>();

        try {
            Notification notification = notificationService.markAsRead(notificationId);
            response.setData(new NotificationResponse(notification));
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request) {
        try {
            emailService.sendEmailWithNotification(request.getUsername(), request.getMessage(), "Assunto Padrão", request.getMessage());
            return ResponseEntity.ok("Notificação salva e e-mail enviado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (MessagingException e) {
            return ResponseEntity.badRequest().body("Erro ao enviar e-mail: " + e.getMessage());
        }
    }

}
