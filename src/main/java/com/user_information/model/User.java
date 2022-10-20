package com.user_information.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {


    private String lastName;
    private String firstName;
    private String patronymic;
    private LocalDate dateBirth;
    private String email;
    private String phoneNamber;
    private String photo;

}
