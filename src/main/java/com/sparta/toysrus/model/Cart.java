package com.sparta.toysrus.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Cart {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToOne
    @Column(name = "userId",nullable = false)
    private User user;

    @OneToMany
    @Column(name = "itemId",nullable = false)
    private List<Item> item;

    @Column(nullable = false)
    private String cartCount;
}
