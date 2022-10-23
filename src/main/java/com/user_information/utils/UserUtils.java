package com.user_information.utils;

import com.user_information.dto.UserContacts;
import com.user_information.dto.UserWithoutTelNbPhoto;
import com.user_information.model.User;
import com.user_information.repository.UserRepository;
import lombok.experimental.UtilityClass;

import javax.persistence.EntityNotFoundException;

@UtilityClass
public class UserUtils {
    public static void checkUserById(UserRepository userRepositoryr, int userId) {
        if (!userRepositoryr.existsById(userId)) {
            throw new EntityNotFoundException("user with specified ID not exists");
        }
    }
    public static User setUserDetails(UserWithoutTelNbPhoto userWithoutTelNbPhoto, User user) {
        user.setLastName(userWithoutTelNbPhoto.lastName());
        user.setFirstName(userWithoutTelNbPhoto.firstName());
        user.setPatronymic(userWithoutTelNbPhoto.patronymic());
        user.setPassword(userWithoutTelNbPhoto.password());
        user.setDateBirth(userWithoutTelNbPhoto.dateBirth());
        return user;
    }
    public static void setUserDetails(User olduser, User user) {
        user.setLastName(olduser.getLastName());
        user.setFirstName(olduser.getFirstName());
        user.setPatronymic(olduser.getPatronymic());
        user.setPassword(olduser.getPassword());
        user.setDateBirth(olduser.getDateBirth());
    }

    public static void setUserContacts(User oldUser, User newuser) {
        newuser.setPhoneNamber(oldUser.getPhoneNamber());
        newuser.setEmail(oldUser.getEmail());
        newuser.setPhoto(oldUser.getPhoto());
    }

    public static void setUserContacts(User user, UserContacts userContacts) {
        user.setPhoneNamber(userContacts.phoneNamber());
        user.setEmail(userContacts.email());
    }
}
