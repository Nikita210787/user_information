package com.user_information.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Entity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {

    @Column(name = "last_name",nullable = false)
    @Size(max = 128)
    private String lastName;

    @Column(name = "first_name",nullable = false)
    @Size(max = 128)
    private String firstName;

    @Column(name = "patronymic",nullable = false)
    @Size(max = 128)
    private String patronymic;

    @Column(name = "dateBirth",nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateBirth;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotEmpty
    @Size(max = 128)
    private String email;

    //@Pattern(regexp="(^$|[0-9]{10})")
    //or we can create @ValidatorPhonenamber //https://www.twilio.com/blog/validating-phone-numbers-spring-boot-twilio-lookup-api
    @Pattern(regexp = "(\\+8|7)[0-9]{10}")
    @Column(name = "phone_namber",unique = true,nullable = false)
    private String phoneNamber;

    @Column(name = "password")
    @Size(max = 128)
    private String photo;

    @Column(name = "password",nullable = false)
    @Size(max = 128)
    private String password;


}
