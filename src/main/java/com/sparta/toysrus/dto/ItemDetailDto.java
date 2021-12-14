package com.sparta.toysrus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDetailDto {
        private Long itemId;
        private String itemName;
        private Long price;
        private Long discount;
        private String thumbnail;
        private String imgDetail;
}
