package com.example.mvc.repository;

import com.example.mvc.models.Role;
import com.example.mvc.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
