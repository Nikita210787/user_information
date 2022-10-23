package com.user_information.utils;

import com.user_information.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtil {

    //  Conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)'
    public static void assureIdConsistent(User user, int id) {
        if (user.isNew()) {
            user.setId(id);
        } else if (user.id() != id) {
            throw new IllegalArgumentException(user.getClass().getSimpleName() + " must has id=" + id);
        }
    }
}
