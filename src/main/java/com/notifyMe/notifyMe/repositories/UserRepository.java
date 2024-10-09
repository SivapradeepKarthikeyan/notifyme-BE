package com.notifyMe.notifyMe.repositories;

import com.notifyMe.notifyMe.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
   //TODO add query here :)
   @Query("SELECT u FROM User u WHERE u.userId = :userId")
   Optional<User> findByUserId(String userId);
}
