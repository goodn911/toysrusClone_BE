package com.sparta.toysrus.repository;

import com.sparta.toysrus.model.Item;
import com.sparta.toysrus.model.ShowAreaEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {
     List<Item> findAllByShowAreaEnum(ShowAreaEnum showAreaEnum);

     Page<Item> findAll(Pageable pageable);

     List<Item> findAllByOrderByClickCountDesc();
}
