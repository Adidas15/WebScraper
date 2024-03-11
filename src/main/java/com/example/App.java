package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) {
        System.setProperty("chromedriver","chromedriver-win64");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");

        driver.quit();
        
        System.out.print("Finished");
    }
}