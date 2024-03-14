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

    public static ArrayList<String> allNames = new ArrayList<String>();
    public static ArrayList<String> allContent = new ArrayList<String>();

    public static void collectData() throws InterruptedException {
        System.setProperty("chromedriver","chromedriver-win64");
        WebDriver driver = new ChromeDriver();
        // driver.get("https://www.amazon.com/Air-Jungles-Freshener-Refresher-Eliminator/product-reviews/B07LGL6LSF/ref=cm_cr_arp_d_viewopt_srt?ie=UTF8&reviewerType=all_reviews&pageNumber=1&sortBy=recent");
        // 

        /*ArrayList<String> allNames = new ArrayList<String>();
        ArrayList<String> allContent = new ArrayList<String>();*/
        try {
            for (int i=1; i <= 5; i++) {
                driver.get("https://www.amazon.com/Air-Jungles-Freshener-Refresher-Eliminator/product-reviews/B07LGL6LSF/ref=cm_cr_arp_d_viewopt_srt?ie=UTF8&reviewerType=all_reviews&pageNumber="+i+"&sortBy=recent");
                Thread.sleep(5000);
                List<WebElement> names = driver.findElements(By.className("a-profile-name"));
                List<WebElement> reviews = driver.findElements(By.className("review-text-content"));
                int a = 0;
                for (WebElement n : names) {
                    
                    if (a>1){
                        allNames.add(n.getText());
                    }
                    a++;
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
        for (int i=0; i < reviews.size(); i++) {
            String text = reviews.get(i).replaceAll("\n", "");
            myWriter.write(names.get(i) + ", " + text + "\n");
        }
        myWriter.close();
    }

    /*public static ArrayList<String> goodContent;
    public static ArrayList<String> goodUsers;

    public static ArrayList<String> badContent;
    public static ArrayList<String> badUsers;*/

    public static void findTargetCustomers() throws IOException {
        FileWriter goodWriter  = new FileWriter("goodtargets.txt");
        FileWriter badWriter = new FileWriter("badtargets.txt");
        for (int i=0; i<allContent.size(); i++) {
            String text = allContent.get(i).replaceAll("\n", "");
            String temp = Review.removePunctuation(text);
            if(Review.totalSentiment(temp) > 0.5) {
                /*goodContent.add(allContent.get(i));
                goodUsers.add(allNames.get(i));*/
                goodWriter.write(allNames.get(i) + ", " + text + "\n");
            }
            else {
                /*badContent.add(allContent.get(i));
                badUsers.add(allContent.get(i));*/
                badWriter.write(allNames.get(i) + ", " + text + "\n");
            }
            
        }
        goodWriter.close();
        badWriter.close();

        
        
    } 

    

    public static void main(String[] args) throws IOException {

        try {
            collectData();
            findTargetCustomers();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        


    }
}