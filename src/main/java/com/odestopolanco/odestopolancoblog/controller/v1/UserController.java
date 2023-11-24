package com.odestopolanco.odestopolancoblog.controller.v1;

import com.odestopolanco.odestopolancoblog.dao.services.UserService;
import com.odestopolanco.odestopolancoblog.domain.User;
import com.odestopolanco.odestopolancoblog.domain.UsersResponse;
import com.odestopolanco.odestopolancoblog.exceptions.ApiRequestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        User createdUser;
        try {
            createdUser = userService.save(newUser);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody User currentUser) {
        User updatedUser;
        try {
            updatedUser = userService.save(currentUser);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
        return ResponseEntity.ok(updatedUser);
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
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
