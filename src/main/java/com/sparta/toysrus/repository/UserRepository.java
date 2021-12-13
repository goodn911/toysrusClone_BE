package com.sparta.toysrus.repository;

import com.sparta.toysrus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
