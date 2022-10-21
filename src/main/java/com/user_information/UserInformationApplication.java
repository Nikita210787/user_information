package com.user_information;

import com.user_information.model.User;
import com.user_information.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class UserInformationApplication implements ApplicationRunner {
    private final UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(UserInformationApplication.class, args);
    }
    @Override
    public void run(ApplicationArguments args) {
                //userRepository.save(new User("Sychev", "Nikita", "Antonovich", "1987-07-21","user@gmail.com","89128529567","1234567890"));
                System.out.println(userRepository.findAll());
            }

}
