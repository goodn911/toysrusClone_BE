package com.sparta.toysrus.controller;


import com.sparta.toysrus.crawling.CrawlingJsoup;
import com.sparta.toysrus.dto.CrawlingDto;
import com.sparta.toysrus.dto.ItemDetailDto;
import com.sparta.toysrus.dto.MainResponseDto;
import com.sparta.toysrus.dto.RakingDto;
import com.sparta.toysrus.model.Item;
import com.sparta.toysrus.service.ItemService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final CrawlingJsoup crawlingJsoup;

    @PostMapping("/api/crawling")
    public void makeItems(@RequestBody CrawlingDto crawlingDto) throws IOException, InterruptedException {

        crawlingJsoup.crawlingAdd(crawlingDto.getCategoryUrl(),crawlingDto.getCategoryName());

    }

    @GetMapping("/api/item/{itemId}")
    public ItemDetailDto getDetail(@PathVariable Long itemId){
        return itemService.getDetail(itemId);
    }

    @GetMapping("/api/item")
    public MainResponseDto getMainItems(@RequestParam int page,@RequestParam int size){
        return itemService.getMainItems(page,size);


    }
    @GetMapping("/api/item/ranking")
    public List<RakingDto> getRanking(){
       return itemService.getRanking();
    }




}
