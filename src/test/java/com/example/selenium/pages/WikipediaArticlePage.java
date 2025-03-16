package com.example.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;

/**
 * Page Object representing a Wikipedia article page
 */
public class WikipediaArticlePage extends BasePage {

    private final WebDriverWait wait;

    // Locators
    private final By pageTitleLocator = By.cssSelector("span.mw-page-title-main");

    /**
     * Constructor for WikipediaArticlePage
     *
     * @param driver WebDriver instance
     * @param wait WebDriverWait instance
     */
    public WikipediaArticlePage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.wait = wait;
    }

    /**
     * Verify the page title element is displayed
     */
    public void verifyPageTitleIsDisplayed() {
        WebElement pageTitle = driver.findElement(pageTitleLocator);
        assertTrue(pageTitle.isDisplayed(), "Page title element should be displayed");
    }

    /**
     * Verify the page title text matches the expected text
     *
     * @param expectedTitleText the expected title text
     */
    public void verifyPageTitleText(String expectedTitleText) {
        WebElement pageTitle = driver.findElement(pageTitleLocator);
        String actualTitleText = pageTitle.getText();
        assertTrue(actualTitleText.equals(expectedTitleText), "Page title text should match '" + expectedTitleText + "'");
    }
    
    /**
     * Verify the HTML document title matches the expected value
     *
     * @param expectedDocumentTitle the expected document title
     */
    public void verifyDocumentTitle(String expectedDocumentTitle) {
        String actualTitle = driver.getTitle();
        assertTrue(actualTitle.equals(expectedDocumentTitle), 
                  "Document title should match '" + expectedDocumentTitle + "', but was '" + actualTitle + "'");
    }
    
    /**
     * Verify the current URL contains the expected text
     *
     * @param expectedUrlFragment text that should be contained in the URL
     */
    public void verifyUrlContains(String expectedUrlFragment) {
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains(expectedUrlFragment), 
                  "URL should contain '" + expectedUrlFragment + "', but was '" + currentUrl + "'");
    }
    
    /**
     * Verify the current URL matches a regex pattern
     *
     * @param urlPattern regex pattern that the URL should match
     */
    public void verifyUrlMatchesPattern(String urlPattern) {
        String currentUrl = driver.getCurrentUrl();
        Pattern pattern = Pattern.compile(urlPattern);
        assertTrue(pattern.matcher(currentUrl).matches(), 
                  "URL should match pattern '" + urlPattern + "', but was '" + currentUrl + "'");
    }
}