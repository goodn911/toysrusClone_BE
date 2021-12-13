package com.sparta.toysrus.repository;

import com.sparta.toysrus.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
