package com.sparta.toysrus.service;

import com.sparta.toysrus.dto.*;
import com.sparta.toysrus.model.Item;
import com.sparta.toysrus.model.ShowAreaEnum;
import com.sparta.toysrus.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void addItem(List<ItemDto> itemDtoList){
        for (ItemDto itemDto:itemDtoList){
            Item item = new Item(itemDto);
            itemRepository.save(item);

        }
    }
    @Transactional
    public ItemDetailDto getDetail(Long itemId) {

        Optional<Item> item = itemRepository.findById(itemId);

        Long itemDetailDtoId = item.get().getId();
        String itemName = item.get().getItemName();
        Long itemPrice = item.get().getPrice();
        Long itemDiscount= item.get().getDiscount();
        String itemThumbnail= item.get().getThumbnail();
        String itemImgDetail = item.get().getImgDetail();

        ItemDetailDto itemDetailDto = new ItemDetailDto(itemDetailDtoId,itemName,itemPrice,itemDiscount,itemThumbnail,itemImgDetail);
        Long clickCount = item.get().getClickCount();

        item.get().setClickCount(clickCount+1);

        return itemDetailDto;

    }

    @Transactional
    public MainResponseDto getMainItems(int page,int size) {
        //메인리스폰스에 들어갈 리스트
        List<ChristmasItemDto> christmasItemDtoList = new ArrayList<>();
        List<HotItemDto> hotItemDtoList = new ArrayList<>();
        List<TimedealDto> timedealDtoList = new ArrayList<>();


        //각 Enum에 해당할 아이템리스트
        List<Item> christmasList = itemRepository.findAllByShowAreaEnum(ShowAreaEnum.CHRISTMAS);
        List<Item> hotdealList = itemRepository.findAllByShowAreaEnum(ShowAreaEnum.HOTDEAL);
        List<Item> timedealList = itemRepository.findAllByShowAreaEnum(ShowAreaEnum.TIMEDEAL);

        for(Item item:christmasList){
            ChristmasItemDto christmasItemDto = new ChristmasItemDto(item.getId(),item.getItemName(),item.getPrice(),item.getThumbnail());
            christmasItemDtoList.add(christmasItemDto);
        }
        for(Item item:hotdealList){
            HotItemDto hotItemDto= new HotItemDto(item.getId(),item.getItemName(),item.getPrice(),item.getThumbnail());
            hotItemDtoList.add(hotItemDto);
        }
        for(Item item:timedealList){
            TimedealDto timedealDto = new TimedealDto(item.getId(),item.getItemName(),item.getPrice(),item.getDiscount(),item.getDescription(),item.getThumbnail());
            timedealDtoList.add(timedealDto);
        }
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Item> findPage = itemRepository.findAll(pageable);
        MainResponseDto mainResponseDto = new MainResponseDto(christmasItemDtoList,timedealDtoList,hotItemDtoList,findPage);

        return  mainResponseDto;


    }
    @Transactional
    public List<RakingDto> getRanking() {

        List<RakingDto> rakingDtoList = new ArrayList<>();
        List<Item> itemList = itemRepository.findAllByOrderByClickCountDesc();
        for (int i =0; i<10;i++){

            RakingDto rakingDto = new RakingDto(itemList.get(i).getItemName());
            rakingDtoList.add(rakingDto);

        }
        return rakingDtoList;


    }
}
