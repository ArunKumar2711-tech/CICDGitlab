package com.yourcompany;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class YourSeleniumTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set the system property for geckodriver
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");

        // Create Firefox options and set necessary preferences
        FirefoxOptions options = new FirefoxOptions();
        options.setLogLevel(FirefoxDriverLogLevel.DEBUG);  // Set log level for debugging
        options.addArguments("--no-sandbox");              // Required for running in some CI environments
        options.addArguments("--headless");                // Run in headless mode (no UI)
        options.addArguments("--disable-dev-shm-usage");   // Prevent Chrome from using too much shared memory

        // Set up the Firefox profile directory
     //   String profilePath = "/root/.mozilla/37i7d57d.Automate";
     //   FirefoxProfile profile = new FirefoxProfile(new File(profilePath));
     //   options.setProfile(profile);
        options.addArguments("-profile"); // Use this to specify the profile
        options.addArguments("/root/.mozilla/37i7d57d.Automate"); // Replace with your actual profile directory
      //  options.addArguments("--no-sandbox");              // Required for running in some CI environments
     //   options.addArguments("--headless");                // Run in headless mode (no UI)
      //  options.addArguments("--disable-dev-shm-usage");   // Prevent Chrome from using too much shared memory

        
        
        
        // Additional Firefox preferences
        options.addPreference("devtools.console.stdout.content", true);  // Send Firefox logs to console

        // Start the FirefoxDriver with the configured options
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  // Implicit wait for 10 seconds
    }

    @Test
    public void openGmail() throws InterruptedException {
        // Open Gmail webpage and verify title
        driver.get("https://www.gmail.com");
        Thread.sleep(3000);  // Wait for the page to load (better approach would be WebDriverWait)
        System.out.println("Page title is: " + driver.getTitle());
        System.out.println("Current URL: " + driver.getCurrentUrl());
        // Uncomment below if you want to assert title
        // assertEquals(driver.getTitle(), "Gmail");
    }

    @Test
    public void openRappitGitlab() {
        // Open Rappit GitLab page and verify title
        driver.get("https://rappit-gitlab.vanenburg.com");
        System.out.println("Page title is:  " + driver.getTitle());
        assertEquals(driver.getTitle(), "GitLab");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser session after tests
        if (driver != null) {
            driver.quit();
        }
    }
}
