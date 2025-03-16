package com.example.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Base test class with common setup and teardown methods.
 * Extend this class to create your Selenium tests.
 */
public class BaseTest {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    // Default wait time in seconds
    protected static final int DEFAULT_WAIT_TIME = 10;
    
    /**
     * Sets up WebDriverManager for all tests
     */
    @BeforeAll
    public static void setupClass() {
        // Setup WebDriverManager for common browsers
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }
    
    /**
     * Sets up the WebDriver instance before each test
     */
    @BeforeEach
    public void setup() {
        // By default, use Chrome. Can be overridden in subclasses
        setupChromeDriver();
    }
    
    /**
     * Tears down the WebDriver instance after each test
     */
    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    
    /**
     * Sets up Chrome WebDriver with default options
     */
    protected void setupChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME));
    }
    
    /**
     * Sets up Firefox WebDriver with default options
     */
    protected void setupFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-width=1920");
        options.addArguments("-height=1080");
        
        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME));
    }
    
    /**
     * Navigates to a URL and maximizes the window
     * 
     * @param url the URL to navigate to
     */
    protected void navigateTo(String url) {
        driver.get(url);
    }
    
    /**
     * Creates a custom WebDriverWait with the specified timeout
     * 
     * @param timeoutInSeconds the timeout in seconds
     * @return a new WebDriverWait instance
     */
    protected WebDriverWait createWait(int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }
} 