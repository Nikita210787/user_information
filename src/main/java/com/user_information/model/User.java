package com.user_information.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.user_information.utils.JsonDeserializers;
import lombok.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.util.ProxyUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * User Entity.
 * Email and phone namber and photo can be null or empty for deleting.
 * photo add like just a stub - it is fo future developing.
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Access(AccessType.FIELD)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "uniq_lNma_fName_patronomic_dateBirth",
                columnNames = {"last_name", "first_name", "patronymic", "date_birth"})})
public class User implements Persistable<Integer>, Serializable {

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

    //TODO // dont work with that pattern
    //@Pattern(regexp="(^$|[0-9]{10})")// @Pattern(regexp = "(\\+8|7)[0-9]{10}")
    //or we can create @ValidatorPhonenamber //https://www.twilio.com/blog/validating-phone-numbers-spring-boot-twilio-lookup-api
    @Column(name = "phone_namber", unique = true, nullable = true)
    private String phoneNamber;

    @Column(name = "photo")
    @Size(max = 128)
    private String photo;

    // @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonDeserialize(using = JsonDeserializers.PasswordDeserializer.class)
    @Column(name = "password", nullable = false)
    @Size(max = 128)
    //@ToString.Exclude
    private String password;

    @Column(name = "date_birth", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateBirth;

    @Column(name = "email", nullable = true, unique = true)
    @Email
    //@NotEmpty
    @Size(max = 128)
    private String email;

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
