package com.sparta.crawlingTest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class crawlingSelenium {

    public static void main(String[] args) throws InterruptedException, AWTException {

        WebDriver driver;
        WebElement element;
        String url;


        // 크롬드라이버 쓸거임
        final String WEB_DRIVER_ID = "webdriver.chrome.driver";
        // 경로
        final String WEB_DRIVER_PATH = "/Users/oh/Documents/Programming/hanghae99/7주차/toysrusClone_BE/src/main/java/com/sparta/toysrus/crawling/chromedriver";

        // 드라이브를 실행할 수 있도록 환경설정
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // 크롬 옵션 설정
        ChromeOptions options = new ChromeOptions();
        // 크롬 드라이버 객체 생성
        driver = new ChromeDriver(options);

        // URL 설정

        url = "https://ohou.se/store";
        driver.get(url);
        // 브라우저가 완전히 로딩될 때까지 시간을 기다려줌
        Thread.sleep(4000);

//        Actions actions = new Actions(driver);
//        actions.sendKeys(Keys.PAGE_DOWN);
//        actions.sendKeys(Keys.PAGE_DOWN);

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,14000)");
        Thread.sleep(2000);
        List<WebElement> list = driver.findElements(By.cssSelector(".production-item__overlay"));
        for (WebElement item : list) {
            System.out.println(item.getAttribute("href"));
        }

        jse.executeScript("window.scrollBy(0,16000)");
        Thread.sleep(2000);
        list = driver.findElements(By.cssSelector(".production-item__overlay"));
        for (WebElement item : list) {
            System.out.println(item.getAttribute("href"));
        }

        jse.executeScript("window.scrollBy(0,18000)");
        Thread.sleep(2000);
        list = driver.findElements(By.cssSelector(".production-item__overlay"));
        for (WebElement item : list) {
            System.out.println(item.getAttribute("href"));
        }

        jse.executeScript("window.scrollBy(0,20000)");
        Thread.sleep(2000);
        list = driver.findElements(By.cssSelector(".production-item__overlay"));
        for (WebElement item : list) {
            System.out.println(item.getAttribute("href"));
        }

//
//        int count =  1;
//        for (WebElement item : list) {
//            driver = new ChromeDriver(options);
//            url = item.getAttribute("href");
//            driver.get(url);
//            String title = "title" + count;
//            String thumbnail = "thumbnail" + count;
//            String price = "price" + count;
//            String imgDetail = "imgDetail" + count;
//
//            Thread.sleep(2500);
//
//            title + Integer.toString(count) = driver.findElement(By.cssSelector("#stickyTopParent > div.productDetailTop > div.productTitle > div.productName > h1")).getText();
//            System.out.println("상품명: " + title);
////            System.out.println("상품명 : " + title.getText());
//
//            thumbnail = driver.findElement(By.cssSelector("#stickyTopParent > div.stickyVisual.vue-affix.affix-top > div > div.swiper-container.largeImgSlide.default.swiper-container-initialized.swiper-container-vertical > div > div > div > img")).getAttribute("src");
//            System.out.println("썸네일: " + thumbnail);
////            System.out.println("썸네일 URL : " + thumbnail.getAttribute("src"));
//
//            price = driver.findElement(By.cssSelector("#stickyTopParent > div.productDetailTop > div:nth-child(2) > div > div.priceInfo > div > span")).getText();
//            System.out.println("가격: " + price);
////            System.out.println("가격 : " + price.getText());
//
//            imgDetail = driver.findElement(By.cssSelector("#m2-prd-frame")).getAttribute("src");
//            System.out.println("상세이미지: " + imgDetail);
////            System.out.println("제품상세이미지 URL : " + imgDetail.getAttribute("src"));
//
//            System.out.println("============================================================");
//
//            count += 1;
//            driver.close();
//        }
    }
}
