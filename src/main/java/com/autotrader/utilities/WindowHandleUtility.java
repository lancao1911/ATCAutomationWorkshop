package com.autotrader.utilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.util.ArrayList;
import java.util.List;

/**
 * A Utility to help with window handling for the ConsumerSite Tests
 */
public class WindowHandleUtility {

    //~ Static fields/initializers -------------------------------------------------------------------------------------

    private static final Log LOGGER = LogFactory.getLog(WindowHandleUtility.class);

    //~ Instance fields ------------------------------------------------------------------------------------------------

    private WebDriver driver;
    private StableActions stableActions;

    //~ Constructors ---------------------------------------------------------------------------------------------------

    /**
     * Creates a new WindowHandleUtility object.
     *
     * @param driver in value
     */
    public WindowHandleUtility(WebDriver driver) {
        this.driver = driver;
        this.stableActions = new StableActions(driver);
    }

    //~ Methods --------------------------------------------------------------------------------------------------------

    /**
     * Returns true if more than one window is open
     *
     * @return boolean value
     */
    public boolean areMultipleWindowsAreOpen() {
        return driver.getWindowHandles().size() > 1;
    }

    /**
     * Closes the current window. The driver window is set to the first window in the set returned by getWindowHandles()
     */
    public void closeCurrentWindow() {
        if (areMultipleWindowsAreOpen()) {
            driver.close();
            for (String windowHandle : driver.getWindowHandles()) {
                driver.switchTo().window(windowHandle);
                LOGGER.info(String.format("#switchToWindow() : title=%s ; url=%s", driver.getTitle(),
                        driver.getCurrentUrl()));
                return;
            }
        } else {
            LOGGER.error("Unable to close window. Only one window is open");
        }
    }

    /**
     * Switches to the most Recent Window that was opened.
     */
    public void switchToMostRecentWindow() {
        List<String> handlesList = new ArrayList<>(driver.getWindowHandles());
        if (handlesList.size() > 1) {
            driver.switchTo().window(handlesList.get(handlesList.size() - 1));
        } else {
            LOGGER.info("The link that was clicked did NOT produce a pop up...");
        }
        WaitFor.waitForSeconds(1);
    }

    /**
     * Method to verify whether the popup has the element you are looking for
     *
     * @param element in value
     *
     * @return out value
     */
    public boolean doesPopUpHaveElement(By element) {
        boolean navigation = false;
        WaitFor.waitForSeconds(2);
        try {
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

            if (tabs.size() < 2) {
                LOGGER.info("#tabs = " + tabs.size());
                return navigation;
            } else {
                driver.switchTo().window(tabs.get(1));
                LOGGER.info("tab(1): " + driver.getTitle());

                navigation = stableActions.doesElementExist(element);

                driver.close();
                driver.switchTo().window(tabs.get(0));
            }
        } catch (WebDriverException exception) {
            LOGGER.info(exception.toString());
        }
        return navigation;
    }
}
