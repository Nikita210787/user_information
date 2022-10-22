package com.user_information.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.util.ProxyUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "users")
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User implements Persistable<Integer> {

    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    //@Schema(accessMode = Schema.AccessMode.READ_ONLY)
    protected Integer id;

    public int id() {
        Assert.notNull(id, "Entity must have id");
        return id;
    }

    @Column(name = "last_name", nullable = false)
    @Size(max = 128)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    @Size(max = 128)
    private String firstName;

    @Column(name = "patronymic", nullable = false)
    @Size(max = 128)
    private String patronymic;

    @Column(name = "date_birth" , nullable = false)
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
    @Column(name = "phone_namber", unique = true, nullable = false)
    private String phoneNamber;

    @Column(name = "photo")
    @Size(max = 128)
    private String photo;

    @Column(name = "password", nullable = false)
    @Size(max = 128)
    private String password;

    @JsonIgnore
    @Override
    public boolean isNew() {
        return id == null;
    }

    //    https://stackoverflow.com/questions/1638723
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(ProxyUtils.getUserClass(o))) {
            return false;
        }
        User that = (User) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }
}
