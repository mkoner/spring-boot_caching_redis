package com.mkoner.rediscache.repository;

import com.mkoner.rediscache.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
