package com.chatapp.backend.repository;

import com.chatapp.backend.entity.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
    Boolean existsByEmail(String email);
}
