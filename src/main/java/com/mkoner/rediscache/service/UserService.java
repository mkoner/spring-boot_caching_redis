package com.mkoner.rediscache.service;

import com.mkoner.rediscache.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User updateUser(User user, Long id);
    List<User> getAllUsers();
    User getUserById(Long id);
    void deleteUser(long id);
}
