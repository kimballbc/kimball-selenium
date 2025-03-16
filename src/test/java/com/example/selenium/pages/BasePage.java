package com.example.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

/**
 * Base Page class with common methods for all pages
 */
import java.util.logging.Logger;

import com.example.selenium.data.TestConstants;

public abstract class BasePage {

    private static final Logger logger = Logger.getLogger(BasePage.class.getName());

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    /**
     * Constructor for BasePage
     *
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10)); // Initialize wait with a timeout of 10 seconds
    }

    /**
     * Verify the current URL matches the expected URL
     *
     * @param expectedUrl the expected URL
     */
    public void verifyUrl(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        assertTrue(actualUrl.equals(expectedUrl), "URL should match the expected URL");
    }

    /**
     * Get all search suggestions from the dropdown
     * 
     * @return List of Strings containing the suggestion texts (title and description)
     */
    public List<String> getSearchSuggestions() {
        try {
            // Wait for the suggestions dropdown to appear
            By suggestionsDropdownLocator = By.className("suggestions-dropdown");
            wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionsDropdownLocator));
            
            // Get all suggestion links
            By suggestionLinksLocator = By.className("suggestion-link");
            List<WebElement> suggestionLinks = driver.findElements(suggestionLinksLocator);
            
            logger.info("Found " + suggestionLinks.size() + " suggestion links");
            
            // Extract text from each suggestion
            List<String> suggestions = new ArrayList<>();
            for (WebElement link : suggestionLinks) {
                // Get title
                WebElement titleElement = link.findElement(By.className("suggestion-title"));
                String title = titleElement.getText();
                
                // Get description
                WebElement descElement = link.findElement(By.className("suggestion-description"));
                String description = descElement.getText();
                
                // Combine title and description
                String fullSuggestion = title + " - " + description;
                suggestions.add(fullSuggestion);
                
                // For debugging
                logger.fine("Found suggestion: " + fullSuggestion);
            }
            
            // Log all suggestions together at INFO level with new lines
            logger.info("List of suggestions: \n" + String.join("\n", suggestions));
            
            return suggestions;
        } catch (Exception e) {
            logger.warning("Error getting search suggestions: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>(); // Return empty list on error
        }
    }

    /**
     * Check if dropdown items are relevant to the search term
     * 
     * @param searchTerm The search term to check relevance against
     * @return true if dropdown items are relevant to the search term
     */
    public boolean areDropdownItemsRelevant(String searchTerm) {
        List<String> suggestions = getSearchSuggestions();
        
        // Log the number of suggestions found
        logger.info("Total suggestions found: " + suggestions.size());
        
        // Check if suggestions are relevant to the search term
        int relevantCount = 0;
        for (String suggestion : suggestions) {
            String lowerCaseSuggestion = suggestion.toLowerCase();
            String lowerCaseSearchTerm = searchTerm.toLowerCase();
            
            if (lowerCaseSuggestion.contains(lowerCaseSearchTerm)) {
                relevantCount++;
            }
        }
        
        // Calculate percentage of relevant suggestions
        double relevancePercentage = (double) relevantCount / suggestions.size();
        logger.info("Relevant suggestions: " + relevantCount + "/" + suggestions.size() + 
                   " (" + (relevancePercentage * 100) + "%)");
        
        // Return true if at least 70% of suggestions are relevant
        return relevancePercentage >= 0.7;
    }

    /**
     * Returns the count of search suggestions
     * 
     * @return the number of suggestions found
     */
    public int getSuggestionCount() {
        return getSearchSuggestions().size();
    }

    /**
     * Checks if there are at least a minimum number of search suggestions
     * 
     * @param minimumCount the minimum expected number of suggestions
     * @return true if at least minimumCount suggestions are found
     */
    public boolean hasMinimumSuggestionCount(int minimumCount) {
        int count = getSuggestionCount();
        logger.info("Found " + count + " suggestions, minimum expected: " + minimumCount);
        return count >= minimumCount;
    }
}