package com.sparta.toysrus.model;

import com.sparta.toysrus.dto.ItemDto;
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

    @Column
    private Long discount;

    @Column
    private String description;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ShowAreaEnum showAreaEnum;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="categoryId")
    private Category category;

    @Column
    private Long clickCount;




    public Item(ItemDto itemDto) {
        this.itemName=itemDto.getItemName();
        this.thumbnail=itemDto.getThumbnail();
        this.imgDetail=itemDto.getImgDetail();
        this.price=itemDto.getPrice();
        this.category= itemDto.getCategory();
        this.discount=0L;
        this.clickCount=0L;
        this.showAreaEnum = ShowAreaEnum.RECOMMEND;

    }
}
