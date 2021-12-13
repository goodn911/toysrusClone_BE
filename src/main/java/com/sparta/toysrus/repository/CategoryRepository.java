package com.sparta.toysrus.repository;

import com.sparta.toysrus.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
