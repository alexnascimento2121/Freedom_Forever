package com.br.ff.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.br.ff.model.Notification;
import com.br.ff.model.Users;
import com.br.ff.repository.NotificationRepository;
import com.br.ff.repository.UsersRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EmailService {
	@Autowired
    private JavaMailSender mailSender;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UsersRepository usersRepository;

    public void sendEmailWithNotification(String userId, String email, String subject, String message) throws MessagingException {
    	// Recupera o usuário
    	Users user = usersRepository.findById(userId)
    		    .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        // Cria uma nova notificação
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setRead(false);

        // Salva a notificação
        notificationRepository.save(notification);

        // Cria o e-mail
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(user.getUsername());
        helper.setSubject(subject);
        helper.setText(message, true);

        // Envia o e-mail
        mailSender.send(mimeMessage);

      
    }
}
