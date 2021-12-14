package com.sparta.toysrus.crawling;

import com.sparta.toysrus.dto.ItemDto;
import com.sparta.toysrus.model.Category;
import com.sparta.toysrus.repository.CategoryRepository;
import com.sparta.toysrus.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CrawlingJsoup {

    private final ItemService itemService;
    private final CategoryRepository categoryRepository;

//    public static void main(String[] args) throws IOException, InterruptedException {
    @Transactional
    public void crawlingAdd(String categoryUrl,String categoryName) throws IOException, InterruptedException {
        //카테고리 먼저저장
        Category category =new Category(categoryName);
        categoryRepository.save(category);

        String url = categoryUrl;


        List<ItemDto> itemDtoList =new ArrayList<>();
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36")
                .header("scheme", "https")
                .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .header("accept-encoding", "gzip, deflate, br")
                .header("accept-language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,es;q=0.6")
                .header("cache-control", "no-cache")
                .header("pragma", "no-cache")
                .header("upgrade-insecure-requests", "1")
                .get();

        Thread.sleep(1000);
        Elements linkList = doc.select(".cunit_t232 > .cunit_prod > .thmb > a");


        for (int i = 0; i < linkList.size()-60; i++) {
            String href = linkList.get(i).attr("href");
            url = "http://www.ssg.com/" + href;
            doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36")
                .header("scheme", "https")
                .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .header("accept-encoding", "gzip, deflate, br")
                .header("accept-language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,es;q=0.6")
                .header("cache-control", "no-cache")
                .header("pragma", "no-cache")
                .header("upgrade-insecure-requests", "1")
                .get();


            Elements title = doc.select("#content > div.cdtl_cm_detail.ty_ssg.react-area > div.cdtl_row_top > div.cdtl_col_rgt > div.cdtl_prd_info > h2");
            if(title.isEmpty()){
                continue;
            }
            System.out.println("상품명: " + title.text());

            Elements thumbnail = doc.select(("#mainImg"));
            System.out.println("썸네일: " + thumbnail.attr("src"));

            Elements price = doc.select(("#content > div.cdtl_cm_detail.ty_ssg.react-area > div.cdtl_row_top > div.cdtl_col_rgt > div.cdtl_info_wrap > div.cdtl_optprice_wrap > div > span.cdtl_old_price > em"));
            if (price.isEmpty()) {
                price = doc.select("#content > div.cdtl_cm_detail.ty_ssg.react-area > div.cdtl_row_top > div.cdtl_col_rgt > div.cdtl_info_wrap > div.cdtl_optprice_wrap > div > span > em");
            }
            System.out.println("가격: " + price.text());

//            Elements original_price = doc.select("#content > div.cdtl_cm_detail.ty_ssg.react-area > div.cdtl_row_top > div.cdtl_col_rgt > div.cdtl_info_wrap > div.cdtl_optprice_wrap > div > span.cdtl_old_price > em");
//            if (!original_price.isEmpty()) {
//                Double discount = (1-((Double.parseDouble(price.text().replace(',','_'))/Double.parseDouble(original_price.text().replace(',','_')))));
//                System.out.println(discount);
//            }

            Elements imgDetail = doc.select("#_ifr_html");
            System.out.println("상세이미지: " + imgDetail.attr("src"));

            System.out.println("============================================================");

            Thread.sleep(3500);
            String title1 =title.text();
            String thumbnail1 ="http:"+thumbnail.attr("src");
            String imgDetail1 ="http://www.ssg.com"+imgDetail.attr("src");
            Long price1 =Long.parseLong((price.text().split(",")[0]+price.text().split(",")[1]));


            ItemDto itemDto = new ItemDto(title1,thumbnail1,imgDetail1,price1,category);
            System.out.println(itemDto.getThumbnail());
            itemDtoList.add(itemDto);
        }

            itemService.addItem(itemDtoList);
    }
}
