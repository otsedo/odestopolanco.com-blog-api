package com.odestopolanco.odestopolancoblog.controller.v1;

import com.odestopolanco.odestopolancoblog.dao.UserService;
import com.odestopolanco.odestopolancoblog.domain.Users;
import com.odestopolanco.odestopolancoblog.domain.UsersResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
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
    public ResponseEntity<Users> getUserById(@PathVariable long id) {
        Users user;
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

    @GetMapping("/users")
    public ResponseEntity<UsersResponse> getAllUsers() {
        UsersResponse users = new UsersResponse();
        try {
            List<Users> userList = userService.findAllUsers();
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

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> createUser(@RequestBody Users newUser) {
        Users createdUser;
        try {
            createdUser = userService.save(newUser);
        } catch (DataAccessException e) {
            log.error("Error while retrieving users: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> updateUser(@RequestBody Users currentUser) {
        Users updatedUser;
        try {
            updatedUser = userService.save(currentUser);
        } catch (DataAccessException e) {
            log.error("Error while updating the  user: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(updatedUser);
    }
}
