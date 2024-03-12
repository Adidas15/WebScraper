package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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

        ArrayList<String> allNames = new ArrayList<String>();
        ArrayList<String> allContent = new ArrayList<String>();
        try {
            for (int i=1; i < 5; i++) {
                driver.get("https://www.amazon.com/Air-Jungles-Freshener-Refresher-Eliminator/product-reviews/B07LGL6LSF/ref=cm_cr_arp_d_paging_btm_next_"+i+"?ie=UTF8&reviewerType=all_reviews&pageNumber="+i);
                Thread.sleep(5000);
                List<WebElement> names = driver.findElements(By.className("a-profile-name"));
                List<WebElement> reviews = driver.findElements(By.className("review-text-content"));
                for (WebElement n : names) {
                    allNames.add(n.getText());
                }
                for (WebElement r : reviews) {
                    allContent.add(r.getText());
                }
            }
            writeFile(allNames, allContent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }


        

        


        
        // System.out.print("Finished");
    }



    public static void writeFile(ArrayList<String> names, ArrayList<String> reviews) throws IOException {
        FileWriter myWriter = new FileWriter("allreviews.txt");
        for (int i=0; i < names.size(); i++) {
            String text = reviews.get(i).replaceAll("\n", "");
            myWriter.write(names.get(i) + ", " + text + "\n");
        }
        myWriter.close();
    }
}