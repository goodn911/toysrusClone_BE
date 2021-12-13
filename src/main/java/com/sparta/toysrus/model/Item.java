package com.sparta.toysrus.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Column
    @Enumerated(value = EnumType.STRING)
    private CategoryEnum categoryEnum;

    @ManyToOne
    private Category category;

    @Column(nullable = false)
    private Long clickCount;

    public Item(String itemName, String thumbnail, String imgDetail, Long price, Long discount, Category category) {
        this.itemName = itemName;
        this.thumbnail = thumbnail;
        this.imgDetail = imgDetail;
        this.price = price;
        this.discount = discount;
        this.category = category;
        this.clickCount = 0L;
    }
}
