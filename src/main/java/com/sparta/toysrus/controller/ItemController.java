package com.sparta.toysrus.controller;


import com.sparta.toysrus.service.ItemService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;




}
