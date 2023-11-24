package com.odestopolanco.odestopolancoblog.dao;

import com.odestopolanco.odestopolancoblog.domain.Users;
import com.odestopolanco.odestopolancoblog.exceptions.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    UsersRepository usersRepository;


    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }

    public Users findUserById(long id) {
        return usersRepository.findUsersById(id);
    }

    public Users save(Users user) {
        Users newUser;
        try {
            newUser = usersRepository.save(user);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
        return newUser;
    }
}
