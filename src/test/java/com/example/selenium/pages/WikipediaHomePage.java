package com.example.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.selenium.data.TestConstants;

/**
 * Page Object representing the Wikipedia homepage
 */
public class WikipediaHomePage extends BasePage {

    private final WebDriverWait wait;

    // Locators
    private static final By searchInput = By.id("searchInput");
    private static final By searchButton = By.xpath("//button[@type='submit' and contains(@class, 'pure-button')]");
    private static final By wikipediaLogo = By.cssSelector("h1.central-textlogo-wrapper span.central-textlogo__image");
    private static final By suggestionsDropdown = By.className("suggestions-dropdown");
    private static final By languageListButton = By.id("js-lang-list-button");
    private static final By languageLists = By.id("js-lang-lists");

    /**
     * Constructor for the Wikipedia Homepage
     *
     * @param driver WebDriver instance
     * @param wait WebDriverWait instance
     */
    public WikipediaHomePage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.wait = wait;
    }

    /**
     * Navigate to Wikipedia homepage
     *
     * @return this page object for method chaining
     */
    public WikipediaHomePage goTo() {
        driver.get(TestConstants.Urls.WIKIPEDIA_HOME);
        return this;
    }

    /**
     * Enter search term in the search box and wait for suggestions to appear
     *
     * @param searchTerm the term to search for
     * @return this page object for method chaining
     */
    public WikipediaHomePage enterSearchTerm(String searchTerm) {
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        searchBox.clear();
        searchBox.sendKeys(searchTerm);

        // Wait for suggestions dropdown to appear
        waitForSuggestionsDropdown();

        return this;
    }

    /**
     * Wait for the suggestions dropdown to appear
     */
    private void waitForSuggestionsDropdown() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionsDropdown));
    }

    /**
     * Click the search button and navigate to the search results page
     *
     * @return WikipediaArticlePage instance
     */
    public WikipediaArticlePage clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        return new WikipediaArticlePage(driver, wait);
    }

    /**
     * Perform a full search (enter search term + click search)
     *
     * @param searchTerm the term to search for
     * @return WikipediaArticlePage instance
     */
    public WikipediaArticlePage searchFor(String searchTerm) {
        return enterSearchTerm(searchTerm).clickSearch();
    }

    /**
     * Get the text of the Wikipedia logo
     *
     * @return the text associated with the Wikipedia logo
     */
    public String getWikipediaLogoText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(wikipediaLogo)).getText();
    }

    /**
     * Get the locator for a suggestion link based on title and description
     *
     * @param title the title of the suggestion
     * @param description the description of the suggestion
     * @return By locator for the specific suggestion
     */
    public static By getSuggestionLinkLocator(String title, String description) {
        return By.xpath(String.format(
            "//a[contains(@class, 'suggestion-link') and .//h3[contains(@class, 'suggestion-title') and text()='%s'] " +
            "and .//p[contains(@class, 'suggestion-description') and text()='%s']]",
            title, description
        ));
    }

    /**
     * Get the suggestion link WebElement based on title and description
     *
     * @param title the title of the suggestion
     * @param description the description of the suggestion
     * @return WebElement of the specific suggestion
     */
    public WebElement getSuggestionLink(String title, String description) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(getSuggestionLinkLocator(title, description)));
    }
    
    /**
     * Click the language list button to display language options
     * 
     * @return this page object for method chaining
     */
    public WikipediaHomePage clickLanguageListButton() {
        wait.until(ExpectedConditions.elementToBeClickable(languageListButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(languageLists));
        return this;
    }
    
    /**
     * Select a language from the language list by its display text
     * 
     * @param languageName the display text of the language to select (e.g., "Español")
     * @return this page object for method chaining
     */
    public WikipediaHomePage selectLanguage(String languageName) {
        By languageLink = By.xpath(String.format("//a[contains(text(), '%s')]", languageName));
        wait.until(ExpectedConditions.elementToBeClickable(languageLink)).click();
        return this;
    }
}