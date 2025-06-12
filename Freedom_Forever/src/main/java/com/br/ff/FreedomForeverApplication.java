package com.br.ff;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.br.ff.model.Users;
import com.br.ff.model.Enum.ProfileEnum;
import com.br.ff.repository.UsersRepository;




@SpringBootApplication
@EnableAsync
public class FreedomForeverApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreedomForeverApplication.class, args);
	}
	
	@Bean // chama o metodo initUsers quando a aplicacao começa rodar.
	CommandLineRunner init(UsersRepository userRepository,PasswordEncoder passwordEncoder) {
		return args->{
			initUsers(userRepository,passwordEncoder);
		};
	}
	
	//metodo que ao iniciar a aplicacao cria um usuario no banco	
	private void initUsers(UsersRepository userRepository,PasswordEncoder passwordEncoder) {
		Users users = new Users();
		users.setName("Alex Nascimento");
		users.setPhone("69993716500");
		users.setUsername("ax@gmail.com");
		users.setPassword(passwordEncoder.encode("123456"));
		users.setBirthDate(LocalDate.of(1995, 5, 10));
		users.setProfile(ProfileEnum.ADMIN);
		users.setPhotoPath("C:/Users/alexn/Pictures/WhatsApp Image 2023-05-09 at 09.55.27.jpeg");
		
		Users find = userRepository.findByUsername("ax@gmail.com");
		if(find == null) {
			userRepository.save(users);
		}		
	}

}
