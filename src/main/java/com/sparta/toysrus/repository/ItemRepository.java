package com.sparta.toysrus.repository;

import com.sparta.toysrus.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
