package com.sparta.toysrus.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecommendItemDto {
    private Long itemId;
    private String itemName;
    private Long price;
    private String thumbnail;

}
