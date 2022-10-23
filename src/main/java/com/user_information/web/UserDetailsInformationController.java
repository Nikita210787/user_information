package com.user_information.web;

import com.user_information.dto.UserWithoutTelNbPhoto;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = UserDetailsInformationController.URL_USER_PROFILE)
@Slf4j
@AllArgsConstructor
public class UserDetailsInformationController {
    public static final String URL_USER_PROFILE = "/api/profile/";
    UserRepository userRepository;

    /**
     * @param id
     * @return details informations for user by id
     */
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserWithoutTelNbPhoto getUserWithoutTelNbPhotoById(@PathVariable int id) {
        log.info("Admin request UserWithoutEmailTelNbPhoto by id:{}", id);
        UserUtils.checkUserById(userRepository, id);
        User user = userRepository.findById(id).get();
        return new UserWithoutTelNbPhoto(user.getLastName(), user.getFirstName(), user.getPatronymic(), user.getPassword(), user.getDateBirth());
    }

    /**
     * @param id
     * @param userWithoutTelNbPhoto
     * @update User details information by id.
     */
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<User> updateDetailInformations(@PathVariable int id, @RequestBody UserWithoutTelNbPhoto userWithoutTelNbPhoto) {
        log.info("update details information user by id {}", id);
        UserUtils.checkUserById(userRepository, id);
        User olduser = userRepository.findById(id).get();
        User user = new User();
        ValidationUtil.assureIdConsistent(user, id);
        UserUtils.setUserContacts(olduser, user);
        UserUtils.setUserDetails(userWithoutTelNbPhoto, user);
        return ResponseEntity.ok(userRepository.save(user));
    }

    /**
     * @param userWithoutTelNbPhoto
     * @return registr new User without contact informations.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> registrDetailInformations(@RequestBody UserWithoutTelNbPhoto userWithoutTelNbPhoto) {
        log.info("register new User ");
        User user = new User();
        UserUtils.setUserDetails(userWithoutTelNbPhoto, user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL_USER_PROFILE)
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(user);
    }

    /**
     * Whole Delete user by id;
     * We can delete just contact information or whole user. User details information will be not null.
     *
     * @param id
     */
    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void wholeDelete(@PathVariable int id) {
        log.info("wholeDelete  any user # {}", id);
        UserUtils.checkUserById(userRepository, id);
        userRepository.deleteById(id);
    }

}