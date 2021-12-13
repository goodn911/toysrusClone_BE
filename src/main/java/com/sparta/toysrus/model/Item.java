package com.sparta.toysrus.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private String thumbnail;

    @Column(nullable = false)
    private String imgDetail;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long discount;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private ShowAreaEum showAreaEum;

    @ManyToOne
    private Category category;

    @Column(nullable = false)
    private Long clickCount;


}
