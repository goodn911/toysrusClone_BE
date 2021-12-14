package com.sparta.toysrus.dto;

import com.sparta.toysrus.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MainResponseDto {

    private List<ChristmasItemDto> christmasProducts;
    private List<TimedealDto> timeLimitProducts;
    private List<HotItemDto> hotProducts;
    private Page<Item> recommendProducts;


}
