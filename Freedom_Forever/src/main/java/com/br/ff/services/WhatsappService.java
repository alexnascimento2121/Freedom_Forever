//package com.br.ff.services;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.br.ff.model.Notification;
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//
//import jakarta.annotation.PostConstruct;
//
//@Service
//public class WhatsappService {
//	@Value("${twilio.account.sid}")
//    private String accountSid;
//
//    @Value("${twilio.auth.token}")
//    private String authToken;
//
//    @Value("${twilio.whatsapp.number}")
//    private String twilioNumber;
//
//    @PostConstruct
//    public void init() {
//        Twilio.init(accountSid, authToken);
//    }
//
//    public void sendWhatsAppNotification(Notification notification, String userPhone) {
//        Message.creator(
//                new PhoneNumber("whatsapp:" + userPhone),
//                new PhoneNumber(twilioNumber),
//                notification.getMessage()
//        ).create();
//    }
//
//	public String getAuthToken() {
//		return authToken;
//	}
//
//	public void setAuthToken(String authToken) {
//		this.authToken = authToken;
//	}
//
//	public String getTwilioNumber() {
//		return twilioNumber;
//	}
//
//	public void setTwilioNumber(String twilioNumber) {
//		this.twilioNumber = twilioNumber;
//	}    
//}
