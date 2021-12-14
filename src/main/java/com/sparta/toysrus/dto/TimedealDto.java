package com.sparta.toysrus.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TimedealDto {
    private Long itemId;
    private String itemName;
    private Long price;
    private Long discount;
    private String description;
    private String thumbnail;

}
