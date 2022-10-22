package com.user_information;

import com.user_information.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@AllArgsConstructor
@EntityScan(basePackages = {"com.user_information.*"})
public class UserInformationApplication implements ApplicationRunner {

    private final UserRepository userRepository;
    public static void main(String[] args) {
        try {
            SpringApplication.run(UserInformationApplication.class, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run(ApplicationArguments args) {
                System.out.println(userRepository.findAll());
            }

}
