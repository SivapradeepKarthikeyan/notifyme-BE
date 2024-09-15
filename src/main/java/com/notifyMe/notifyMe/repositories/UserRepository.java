package com.notifyMe.notifyMe.repositories;

import com.notifyMe.notifyMe.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
   Optional<User> findByUserId(String userId);
}
