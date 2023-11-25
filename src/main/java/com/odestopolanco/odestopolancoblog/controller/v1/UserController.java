package com.odestopolanco.odestopolancoblog.controller.v1;

import com.odestopolanco.odestopolancoblog.dao.services.UserService;
import com.odestopolanco.odestopolancoblog.domain.User;
import com.odestopolanco.odestopolancoblog.domain.UsersResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


/**
 * Main entry point for the blog controller
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class UserController {

    private final UserService userService;


    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        User user;
        try {
            user = userService.findUserById(id);
            if (Objects.isNull(user)) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (DataAccessException e) {
            log.error("Error while retrieving user {}: ", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        User createdUser;
        try {
            createdUser = userService.save(newUser);
        } catch (DataAccessException e) {
            log.error("Error while retrieving users: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody User updatedUser, @PathVariable long userId) {
        try {
            var currentUser = userService.findUserById(userId);
            if (!Objects.isNull(currentUser)) {
                currentUser.setUpdatedAt(LocalDateTime.now());
                currentUser.setProfilePicture(updatedUser.getProfilePicture());
                currentUser.setEmail(updatedUser.getEmail());
                currentUser.setFirstName(updatedUser.getFirstName());
                currentUser.setLastName(updatedUser.getLastName());
                userService.save(currentUser);
                return ResponseEntity.ok(currentUser);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (DataAccessException e) {
            log.error("Error while updating the  user: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUser(@PathVariable long userId) {
        try {
            var currentUser = userService.findUserById(userId);
            if (!Objects.isNull(currentUser)) {
                currentUser.setUpdatedAt(LocalDateTime.now());
                currentUser.setActive(false);
                userService.save(currentUser);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (DataAccessException e) {
            log.error("Error while updating the  user: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/users")
    public ResponseEntity<UsersResponse> getAllUsers() {
        UsersResponse users = new UsersResponse();
        try {
            List<User> userList = userService.findAllUsers();
            if (userList != null) {
                users.setUsersList(userList);
                users.setUsersCount(userList.size());
                return ResponseEntity.ok(users);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (DataAccessException e) {
            log.error("Error while retrieving users: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
