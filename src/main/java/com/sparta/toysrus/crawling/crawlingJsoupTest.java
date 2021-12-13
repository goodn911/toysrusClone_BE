package com.sparta.toysrus.crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;

public class crawlingJsoupTest {
    public static void main(String[] args) throws IOException, InterruptedException {

        String url = "http://www.ssg.com/disp/category.ssg?ctgId=6000161290";
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

        for (int i = 0; i < linkList.size(); i++) {
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

        }
    }
}