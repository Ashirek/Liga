package com.sieczka.repository;

import com.sieczka.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Patryk on 2017-11-22.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}