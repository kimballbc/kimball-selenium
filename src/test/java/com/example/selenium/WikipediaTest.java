package com.example.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import com.example.selenium.data.TestConstants;
import com.example.selenium.pages.WikipediaArticlePage;
import com.example.selenium.pages.WikipediaHomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for Wikipedia functionality
 */
public class WikipediaTest extends BaseTest {

    /**
     * Helper method to get a fresh instance of the Wikipedia home page
     * 
     * @return initialized WikipediaHomePage
     */
    private WikipediaHomePage getWikipediaHomePage() {
        WikipediaHomePage homePage = new WikipediaHomePage(driver, wait);
        homePage.goTo();
        return homePage;
    }

    /**
     * Tests that the Wikipedia home page logo is present
     */
    @Test
    public void testWikipediaHomePageLogoIsPresent() {
        // Create an instance of the WikipediaHomePage
        WikipediaHomePage homePage = getWikipediaHomePage();

        // Verify the presence of the logo with associated text
        String headerText = homePage.getWikipediaLogoText();
        assertTrue(headerText.contains(TestConstants.Text.WIKIPEDIA), "Header should contain 'Wikipedia'");
    }

    /**
     * Tests exact match search functionality on Wikipedia
     */
    @Test
    public void testWikipediaExactMatchSearch() {
        // Create an instance of the WikipediaHomePage and WikipediaArticlePage
        WikipediaHomePage homePage = getWikipediaHomePage();
        WikipediaArticlePage articlePage = new WikipediaArticlePage(driver, wait);

        // Click and enter text into search input
        homePage.enterSearchTerm(TestConstants.Search.SELENIUM_SOFTWARE);

        // verify search results
        WebElement searchResults = homePage.getSuggestionLink(
            TestConstants.Search.SELENIUM_SOFTWARE, 
            TestConstants.Search.SELENIUM_DESCRIPTION
        );
        assertTrue(searchResults.isDisplayed(), "Expected search result is displayed");

        // select result
        searchResults.click();

        // verify correct article is displayed
        articlePage.verifyUrl(TestConstants.Urls.SELENIUM_SOFTWARE_ARTICLE);
        articlePage.verifyPageTitleIsDisplayed();
        articlePage.verifyPageTitleText(TestConstants.Search.SELENIUM_SOFTWARE);
    }

    /**
     * Tests that search suggestions match the search criteria
     */
    @Test
    public void testWikipediaSearchSuggestionsMatchCriteria() {
        // Create an instance of the WikipediaHomePage
        WikipediaHomePage homePage = getWikipediaHomePage();

        // Click and enter text into search input
        homePage.enterSearchTerm(TestConstants.Search.JAVA);
        
        // Verify search suggestions are relevant to the search term
        assertTrue(homePage.areDropdownItemsRelevant(TestConstants.Search.JAVA), 
                   "Search suggestions should be relevant to the search term");
    }
    
    /**
     * Tests changing the Wikipedia language to Spanish
     */
    @Test
    public void testChangeLanguageToSpanish() {
        // Create an instance of the WikipediaHomePage
        WikipediaHomePage homePage = getWikipediaHomePage();
        WikipediaArticlePage articlePage = new WikipediaArticlePage(driver, wait);
        
        // Click language list button and select Spanish
        homePage.clickLanguageListButton()
                .selectLanguage(TestConstants.Text.SPANISH_LANGUAGE);
        
        // Verify we've been redirected to the Spanish Wikipedia
        articlePage.verifyUrlContains(TestConstants.Urls.SPANISH_WIKIPEDIA);
        articlePage.verifyDocumentTitle(TestConstants.Text.SPANISH_WIKIPEDIA_TITLE);
    }
}