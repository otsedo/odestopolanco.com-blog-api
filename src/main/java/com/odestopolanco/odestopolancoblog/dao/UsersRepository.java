package com.odestopolanco.odestopolancoblog.dao;

import com.odestopolanco.odestopolancoblog.domain.Users;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UsersRepository extends PagingAndSortingRepository<Users,Long> {


    List<Users> findAll();
    Users findUsersById(long id);
    Users save(Users user);


}
