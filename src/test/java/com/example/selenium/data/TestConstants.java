package com.example.selenium.data;

/**
 * This class contains all test constants used in the test suite.
 * Having constants centralized makes tests more maintainable and easier to update.
 */
public class TestConstants {
    
    /**
     * URLs used in tests
     */
    public static class Urls {
        public static final String WIKIPEDIA_HOME = "https://www.wikipedia.org/";
        public static final String SELENIUM_SOFTWARE_ARTICLE = "https://en.wikipedia.org/wiki/Selenium_(software)";
        public static final String SPANISH_WIKIPEDIA = "https://es.wikipedia.org";
    }
    
    /**
     * Search related constants
     */
    public static class Search {
        public static final String SELENIUM_SOFTWARE = "Selenium (software)";
        public static final String SELENIUM_DESCRIPTION = "Testing framework for web applications";
        public static final String JAVA = "Java";
    }
    
    /**
     * Text constants for verification
     */
    public static class Text {
        public static final String WIKIPEDIA = "Wikipedia";
        public static final String SPANISH_LANGUAGE = "Español";
        public static final String SPANISH_WIKIPEDIA_TITLE = "Wikipedia, la enciclopedia libre";
    }
    
    /**
     * Element selectors and CSS classes
     * Note: These are moved here from page objects if they represent business values
     * rather than implementation details
     */
    public static class Elements {
        // If needed, add element-related constants here that represent business values
    }
} 