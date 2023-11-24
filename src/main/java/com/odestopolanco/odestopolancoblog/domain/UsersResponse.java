package com.odestopolanco.odestopolancoblog.domain;

import lombok.Data;

import java.util.List;

@Data
public class UsersResponse {
    private int usersCount;
    private List<Users> usersList;
}
