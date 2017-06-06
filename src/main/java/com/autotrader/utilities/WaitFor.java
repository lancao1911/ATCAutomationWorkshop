package com.autotrader.utilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Class for wait methods
 */
public class WaitFor {

    //~ Static fields/initializers -------------------------------------------------------------------------------------

    private static final Log LOGGER = LogFactory.getLog(WaitFor.class);
    private static final int DEFAULT_WAIT_TIME = 30;

    //~ Constructors ---------------------------------------------------------------------------------------------------

    /**
     * Creates a new WaitFor object.
     */
    private WaitFor() {
    }

    //~ Methods --------------------------------------------------------------------------------------------------------

    /**
     * Wait for the DOM to be ready
     *
     * @param driver in value
     */
    public static void waitForDocumentReady(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until((ExpectedCondition<Boolean>) webDriver -> {
            if ((webDriver) != null) {
                return "complete".equals(
                    ((JavascriptExecutor) webDriver).executeScript("return document.readyState"));
            } else {
                return null;
            }
        });
    }

    /**
     * Wait for an element to be present
     *
     * @param driver in value
     * @param by in value
     */
    public static void waitForElementToBePresent(WebDriver driver, By by) {
        (new WebDriverWait(driver, DEFAULT_WAIT_TIME)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Wait for a certain element to be clickable
     *
     * @param driver in value
     * @param by in value
     */
    public static void waitForExpectedConditions(WebDriver driver, By by) {
        WebElement ruThere = driver.findElement(by);
        (new WebDriverWait(driver, DEFAULT_WAIT_TIME)).until(ExpectedConditions.elementToBeClickable(ruThere));
    }

    /**
     * Wait for some milliseconds
     *
     * @param milliSeconds in value
     */
    public static void waitForMiliseconds(long milliSeconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliSeconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error("Error in waitForSeconds", e);
        }
    }

    /**
     * Wait for some seconds
     *
     * @param seconds in value
     */
    public static void waitForSeconds(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error("Error in waitForSeconds", e);
        }
    }
}
