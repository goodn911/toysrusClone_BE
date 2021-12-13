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
    @Column(name = "userId",nullable = true)
    private User user;

    @OneToMany
    @Column(name = "itemId",nullable = true)
    private List<Item> item;

}
