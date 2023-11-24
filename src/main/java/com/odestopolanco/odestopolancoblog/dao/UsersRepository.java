package com.odestopolanco.odestopolancoblog.dao;

import com.odestopolanco.odestopolancoblog.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UsersRepository extends PagingAndSortingRepository<User, Long> {
    List<User> findAll();

    User findUsersById(long id);

    User save(User user);
}
