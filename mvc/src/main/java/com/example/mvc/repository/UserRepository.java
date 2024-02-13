package com.example.mvc.repository;

import com.example.mvc.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String user);
    UserEntity  findFirstByUsername(String username);

}
