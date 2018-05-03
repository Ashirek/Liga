package com.sieczka.service;

import com.sieczka.model.User;

import java.util.List;

/**
 * Created by Patryk on 2017-11-22.
 */
public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
}
