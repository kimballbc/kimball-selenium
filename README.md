# Selenium Test Project

This project contains automated browser tests using Selenium WebDriver and Java.

In this project, you'll find a few tests for Wikipedia.org.

testWikipediaHomePageLogoIsPresent:

 - This test was essentially my "hello world" for this repo, to get a simple test up and running before building out more complex features.
   
testWikipediaSearchSuggestionsMatchCriteria:
 - Wikipedia provides the user with typeahead searching, where it will present a list of possible search requests based off of what has already been entered in.
 - This test will enter a search term that will generate multiple results, gather those results in a list, and check each one for a substring match of the search term.
 - If the returned list has a 70% or greater match rate, it is considered a success.  This buffer was added after it was noticed not all returned results have an immediate match to the term.

testWikipediaExactMatchSearch:
 - This test checks against an exact match for a search term validates the result takes the user to the correct article.

testChangeLanguageToSpanish:
 - With the ability to search Wikipedia in multiple languages, I wanted to ensure that selecting one would provide the expected outcome in the form of a `es` prefixed URL
 - as well as a tab title with the correct Spanish translation. 
## Setup

This project uses Maven for dependency management. The main dependencies are:

- Selenium WebDriver (4.17.0) for browser automation
- WebDriverManager (5.6.3) for automatic driver management
- JUnit 5 (5.10.2) for test assertions and running

## Project Structure

```
src/
├── main/
│   └── java/        # Main application code (if needed)
└── test/
    └── java/        # Test classes 
```

## Running Tests

To run tests using Maven:

```bash
mvn clean test
```

## Prerequisites

- Java 11 or higher
- Maven
- Web browsers (Chrome, Firefox, etc.) 
