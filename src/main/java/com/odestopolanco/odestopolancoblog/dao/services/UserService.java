package com.odestopolanco.odestopolancoblog.dao.services;

import com.odestopolanco.odestopolancoblog.dao.UsersRepository;
import com.odestopolanco.odestopolancoblog.domain.User;
import com.odestopolanco.odestopolancoblog.exceptions.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    UsersRepository usersRepository;


    public List<User> findAllUsers() {
        return usersRepository.findAll();
    }

    public User findUserById(long id) {
        return usersRepository.findUsersById(id);
    }

    public User findUserByEmail(String email) {
        return usersRepository.findUsersByEmail(email);
    }

    public User save(User user) {
        User newUser;
        user.setActive(true);
        try {
            newUser = usersRepository.save(user);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
        return newUser;
    }
}
