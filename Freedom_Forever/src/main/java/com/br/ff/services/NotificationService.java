package com.br.ff.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ff.model.Notification;
import com.br.ff.model.Users;
import com.br.ff.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    
    @Autowired
    private UsersService usersService;

    public List<Notification> getUnreadNotifications(String username) {
        // 1. Buscar o usuário pelo username
        Users user = usersService.findByUsername(username);

        // 2. Verificar se o usuário foi encontrado
        if (user == null) {
            // Lançar uma exceção se o usuário não for encontrado
            throw new RuntimeException("Usuário não encontrado com username: " + username);
        }

        // 3. Usar o ID do usuário encontrado para buscar as notificações
        return notificationRepository.findByUser_IdAndReadFalse(user.getId());
    }


    public Notification createNotification(String username, String message) {
        // 1. Buscar o usuário pelo username
        Users user = usersService.findByUsername(username);

        // 2. Verificar se o usuário foi encontrado
        if (user == null) {
            // Lidar com o caso de usuário não encontrado. 
            // Você pode lançar uma exceção específica ou retornar null/Optional vazio.
            // Lançar uma exceção é comum para indicar um erro na requisição.
            throw new RuntimeException("Usuário não encontrado com username: " + username);
        }

        // 3. Criar a notificação com o objeto Users encontrado
        Notification notification = new Notification(user, message);
        
        // 4. Salvar a notificação
        return notificationRepository.save(notification);
    }

    public Notification markAsRead(String notificationId) {
        Optional<Notification> notificationOpt = notificationRepository.findById(notificationId);
        if (notificationOpt.isPresent()) {
            Notification notification = notificationOpt.get();
            notification.setRead(true);
            return notificationRepository.save(notification);
        }
        throw new RuntimeException("Notificação não encontrada");
    }

    public Optional<Notification> findById(String id) {
        return notificationRepository.findById(id);
    }
}
