package com.user_information.web;

import com.user_information.model.User;
import com.user_information.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = RootController.URL_ROOT)
@AllArgsConstructor
@Slf4j
public class RootController {
    public static final String URL_ROOT = "/api/users";

    UserRepository userRepository;

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    List<User> getAllUser(){
        log.info("getAllUsers");
        return userRepository.findAll();
    }
}
