package com.example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("chromedriver","chromedriver-win64");
        WebDriver driver = new ChromeDriver();
        // driver.get("https://www.amazon.com/Air-Jungles-Freshener-Refresher-Eliminator/product-reviews/B07LGL6LSF/ref=cm_cr_arp_d_paging_btm_next_1?ie=UTF8&reviewerType=all_reviews&pageNumber=1");
        // 
        for (int i=1; i < 5; i++) {
            driver.get("https://www.amazon.com/Air-Jungles-Freshener-Refresher-Eliminator/product-reviews/B07LGL6LSF/ref=cm_cr_arp_d_paging_btm_next_"+i+"?ie=UTF8&reviewerType=all_reviews&pageNumber="+i);
            Thread.sleep(5000);
        }
        driver.quit();
        
        // System.out.print("Finished");
    }
}