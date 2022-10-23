package com.user_information.web;

import com.user_information.dto.UserContacts;
import com.user_information.model.User;
import com.user_information.repository.UserRepository;
import com.user_information.utils.UserUtils;
import com.user_information.utils.ValidationUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = UserContactController.URL_USER_CONTACT)
@AllArgsConstructor
@Slf4j
public class UserContactController {
    public static final String URL_USER_CONTACT = "/api/contact/user/";
    UserRepository userRepository;

    /**
     * @param id
     * @return Contact informations for user by id
     */
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserContacts getContactById(@PathVariable int id) {
        log.info("Admin request User by id:{}", id);
        UserUtils.checkUserById(userRepository, id);
        User user = userRepository.findById(id).get();
        return new UserContacts(user.getEmail(), user.getPhoneNamber());
    }

    /**
     * @param id
     * @param userContacts
     * @update User contact information by id.
     */
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<User> updateContactInformations(@PathVariable int id, @RequestBody UserContacts userContacts) {
        log.info("update contact information user by id {}", id);

        UserUtils.checkUserById(userRepository, id);
        User olduser = userRepository.findById(id).get();
        User user = new User();

        ValidationUtil.assureIdConsistent(user, id);
        UserUtils.setUserContacts(user, userContacts);
        UserUtils.setUserDetails(olduser, user);

        user.setPhoto(olduser.getPhoto());// just a stub
        return ResponseEntity.ok(userRepository.save(user));
    }

    /**
     * Deleting user contact informations  by id
     *
     * @param id
     */
    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void wholeDelete(@PathVariable int id) {
        log.info("Delete contact informations any user by id {}", id);
        UserUtils.checkUserById(userRepository, id);
        User olduser = userRepository.findById(id).get();
        User user = new User();
        ValidationUtil.assureIdConsistent(user, id);
        UserUtils.setUserDetails(olduser, user);
        user.setPhoneNamber(null);
        user.setEmail(null);
        userRepository.save(user);
    }

}
