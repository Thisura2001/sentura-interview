package com.example.senturainterview.Repository;

import com.example.senturainterview.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity,Long> {
}
