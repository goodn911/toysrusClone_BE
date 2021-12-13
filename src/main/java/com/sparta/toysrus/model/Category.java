package com.sparta.toysrus.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToMany
    @Column(name = "itemId",nullable = false)
    private List<Item> item;

    @Column(nullable = false)
    private String categoryName;
}
