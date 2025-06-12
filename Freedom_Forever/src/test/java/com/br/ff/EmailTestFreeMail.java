package com.br.ff;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.ff.services.EmailService;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;

@SpringBootTest
public class EmailTestFreeMail {
	    @Autowired
	    private EmailService emailService;  // Seu serviço de e-mail real

	    private static GreenMail greenMail;

	    @BeforeAll
	    static void startMailServer() {
	        greenMail = new GreenMail(new ServerSetup(3025, "localhost", "smtp"));
	        greenMail.start();
	    }

	    @AfterAll
	    static void stopMailServer() {
	        greenMail.stop();
	    }

	    @Test
	    public void testSendEmail() throws Exception {
	        // Enviar e-mail usando o serviço real
	        emailService.sendEmailWithNotification("1", "test@example.com", "Test Subject", "Test Content");

	        // Verificar se o e-mail foi realmente enviado
	        javax.mail.internet.MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
	        assertEquals(1, receivedMessages.length);
	        assertEquals("Test Subject", receivedMessages[0].getSubject());
	        assertTrue(receivedMessages[0].getContent().toString().contains("Test Content"));
	    }

}
