package com.autotrader.utilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class utilizes fluent wait to wait for an element and then perform an operation on it.
 */
public class StableActions {

    //~ Static fields/initializers -------------------------------------------------------------------------------------

    private static final Log LOGGER = LogFactory.getLog(StableActions.class);

    //String constants
    private static final String INDEX_TOO_LARGE_MSG = " was greater than the size of the list!";

    //~ Instance fields ------------------------------------------------------------------------------------------------

    private Actions actions;
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    //~ Constructors ---------------------------------------------------------------------------------------------------

    /**
     * Creates a new StableActions object.
     *
     * @param driver in value
     */
    public StableActions(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.webDriverWait = new WebDriverWait(driver, 10);
    }

    //~ Methods --------------------------------------------------------------------------------------------------------

    public String getAttributeValue(By by, String attribute) {
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by)).getAttribute(attribute);
    }

    public String getAttributeValue(WebElement webElement, String attribute) {
        return webDriverWait.until(ExpectedConditions.visibilityOf(webElement)).getAttribute(attribute);
    }

    /**
     * Checks whether or not an element is displayed (ie. not hidden) on page.  If not, waits a short time for element
     * to become displayed.  Returns true once displayed.  Returns false if wait times out without element becoming
     * displayed.  Throws a WebDriverException <b>immediately</b> if element does not currently exist on page.
     *
     * @param by selector of element in question
     *
     * @return True if element is displayed currently in page
     */
    public boolean isDisplayed(By by) {
        try {
            return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
        }catch (TimeoutException e){
            LOGGER.error("Element was not found." + e);
            return false;
        }
    }

    public boolean isDisplayed(WebElement webElement) {
        return webDriverWait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
    }

    public boolean isEnabled(By by) {
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by)).isEnabled();
    }

    public boolean isEnabled(WebElement webElement) {
        return webDriverWait.until(ExpectedConditions.visibilityOf(webElement)).isEnabled();
    }

    public int getNumberOfListElements(By by) {
        return webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by)).size();
    }

    public int getNumberOfListElements(List<WebElement> webElementList) {
        return webDriverWait.until(ExpectedConditions.visibilityOfAllElements(webElementList)).size();
    }

    public int getNumberOfOptionsInDropDownList(By by) {
        return new Select(webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by))).getOptions().size();
    }

    public int getNumberOfOptionsInDropDownList(WebElement webElement) {
        return new Select(webDriverWait.until(ExpectedConditions.visibilityOf(webElement))).getOptions().size();
    }

    public String getSelectedTextFromDropDown(By by) {
        return new Select(webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by))).getFirstSelectedOption()
                .getText();
    }

    public String getSelectedTextFromDropDown(WebElement webElement) {
        return new Select(webDriverWait.until(ExpectedConditions.visibilityOf(webElement))).getFirstSelectedOption()
                .getText();
    }

    public String getText(By by) {
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by)).getText();
    }

    public String getText(WebElement webElement) {
        return webDriverWait.until(ExpectedConditions.visibilityOf(webElement)).getText();
    }

    public List<String> getTextFromAllElementsInList(By by) {
        List<String> textList = new ArrayList<>();
        List<WebElement> list = webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        for (WebElement we : list) {
            textList.add(getText(we).trim());
        }
        return textList;
    }

    public List<String> getTextFromAllElementsInList(List<WebElement> webElementList) {
        List<String> textList = new ArrayList<>();
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(webElementList));
        for (WebElement we : webElementList) {
            textList.add(getText(we).trim());
        }
        return textList;
    }

    public String getTextFromListElementByIndex(By by, int index) {
        String text = null;
        List<WebElement> list = webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        if (index < list.size()) {
            text = getText(list.get(index)).trim();
        } else {
            LOGGER.error(index + INDEX_TOO_LARGE_MSG);
        }
        return text;
    }

    public String getTextFromListElementByIndex(List<WebElement> webElementList, int index) {
        String text = null;
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(webElementList));
        if (index < webElementList.size()) {
            text = getText(webElementList.get(index)).trim();
        } else {
            LOGGER.error(index + INDEX_TOO_LARGE_MSG);
        }
        return text;
    }

    /**
     * Waits for the element to be clickable and then moves to that element and clicks it
     *
     * @param by in value
     */
    public void click(By by) {
        actions.moveToElement(webDriverWait.until(ExpectedConditions.elementToBeClickable(by))).click().build()
            .perform();
    }

    /**
     * Waits for the element to be clickable and then moves to that element and clicks it
     *
     * @param webElement in value
     */
    public void click(WebElement webElement) {
        actions.moveToElement(webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement))).click().build()
            .perform();
    }

    /**
     * Waits for each element in a list to be clickable and clicks
     *
     * @param by in value
     */
    public void clickAllElementsInList(By by) {
        List<WebElement> list = webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        for (WebElement we : list) {
            click(we);
        }
    }

    /**
     * Waits for each element in a list to be clickable and clicks
     *
     * @param webElementList in value
     */
    public void clickAllElementsInList(List<WebElement> webElementList) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(webElementList));
        for (WebElement we : webElementList) {
            click(we);
        }
    }

    /**
     * Waits for an element chosen from a list by its index to be clickable and clicks
     *
     * @param by in value
     * @param index in value
     */
    public void clickListElementByIndex(By by, int index) {
        List<WebElement> list = webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        if (index < list.size()) {
            click(list.get(index));
        } else {
            LOGGER.error(index + INDEX_TOO_LARGE_MSG);
        }
    }

    /**
     * Waits for an element chosen from a list by its index to be clickable and clicks
     *
     * @param webElementList in value
     * @param index in value
     */
    public void clickListElementByIndex(List<WebElement> webElementList, int index) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(webElementList));
        if (index < webElementList.size()) {
            click(webElementList.get(index));
        } else {
            LOGGER.error(index + INDEX_TOO_LARGE_MSG);
        }
    }

    /**
     * Waits for an element chosen by text to be clickable and clicks
     *
     * @param by in value
     * @param text in value
     */
    public void clickListElementByText(By by, String text) {
        List<WebElement> list = webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        for (WebElement webElement : list) {
            if (webElement.getText().equals(text)) {
                click(webElement);
                return;
            }
        }
        LOGGER.error(text + " was not found in the list!");
    }

    /**
     * Waits for an element chosen by text to be clickable and clicks
     *
     * @param webElementList in value
     * @param text in value
     */
    public void clickListElementByText(List<WebElement> webElementList, String text) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(webElementList));
        for (WebElement webElement : webElementList) {
            if (webElement.getText().equals(text)) {
                click(webElement);
                return;
            }
        }
        LOGGER.error(text + " was not found in the list!");
    }

    /**
     * Waits for a random element in a list to be clickable and clicks
     *
     * @param by in value
     *
     * @return out value
     */
    public String clickRandomListElement(By by) {
        List<WebElement> list = webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        String webElementText;
        WebElement webElement = list.get(new Random().nextInt(list.size()));
        webElementText = webElement.getText();
        click(webElement);
        return webElementText;
    }

    /**
     * Waits for a random element in a list to be clickable and clicks
     *
     * @param webElementList in value
     *
     * @return out value
     */
    public String clickRandomListElement(List<WebElement> webElementList) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(webElementList));
        String webElementText;
        WebElement webElement = webElementList.get(new Random().nextInt(webElementList.size()));
        webElementText = webElement.getText();
        click(webElement);
        return webElementText;
    }

    /**
     * Checks if an Element exists.  Checks immediately, with no wait.
     *
     * @param by selector of element in question
     *
     * @return True if element exists currently in driver's page
     */
    public boolean doesElementExist(By by) {
        return !driver.findElements(by).isEmpty();
    }

    /**
     * hover over an element to trigger action
     */
    public void moveToElement(By by) {
        actions.moveToElement(webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by))).build().perform();
    }

    /**
     * Waits for element to be clickable and right clicks
     *
     * @param by in value
     */
    public void rightClick(By by) {
        actions.moveToElement(webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by))).contextClick()
            .build().perform();
    }

    /**
     * Waits for element to be clickable and right clicks
     *
     * @param webElement in value
     */
    public void rightClick(WebElement webElement) {
        actions.moveToElement(webDriverWait.until(ExpectedConditions.visibilityOf(webElement))).contextClick().build()
            .perform();
    }

    /**
     * Waits for chosen option in a dropdown by index to be clickable and selects it
     *
     * @param by in value
     * @param index in value
     */
    public void selectElementFromDropDownByIndex(By by, int index) {
        Select select = new Select(webDriverWait.until(ExpectedConditions.elementToBeClickable(by)));
        if (index < select.getOptions().size()) {
            select.selectByIndex(index);
        } else {
            LOGGER.error(index + " was greater than the number of options in the drop down list!");
        }
    }

    /**
     * Waits for chosen option in a dropdown by index to be clickable and selects it
     *
     * @param webElement in value
     * @param index in value
     */
    public void selectElementFromDropDownByIndex(WebElement webElement, int index) {
        Select select = new Select(webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement)));
        if (index < select.getOptions().size()) {
            select.selectByIndex(index);
        } else {
            LOGGER.error(index + " was greater than the number of options in the drop down list!");
        }
    }

    /**
     * Waits for chosen option in a dropdown by text to be clickable and selects it
     *
     * @param by in value
     * @param text in value
     */
    public void selectElementFromDropDownByText(By by, String text) {
        Select select = new Select(webDriverWait.until(ExpectedConditions.elementToBeClickable(by)));
        try {
            select.selectByVisibleText(text);
        } catch (ElementNotVisibleException e) {
            LOGGER.error(text + " was not found in the drop down list!", e);
        }
    }

    /**
     * Waits for chosen option in a dropdown by text to be clickable and selects it
     *
     * @param webElement in value
     * @param text in value
     */
    public void selectElementFromDropDownByText(WebElement webElement, String text) {
        Select select = new Select(webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement)));
        try {
            select.selectByVisibleText(text);
        } catch (ElementNotVisibleException e) {
            LOGGER.error(text + " was not found in the drop down list!", e);
        }
    }

    /**
     * Will take a by of a SelectBox/Dropdown. Wait for it to be clickable and then click a random option within it
     *
     * @param by in value
     *
     * @return String
     */
    public String selectRandomElementFromDropDown(By by) {
        Select select = new Select(webDriverWait.until(ExpectedConditions.elementToBeClickable(by)));
        select.selectByIndex(new Random().nextInt(select.getOptions().size()));
        return select.getFirstSelectedOption().getText();
    }

    /**
     * Assigns indexed by selector to a web element, waits for input box to be present, clears it, and sends text
     *
     * @param by in value
     * @param text in value
     */
    public void sendKeysByIndex(By by, String text, int index) {
        WebElement webElement = driver.findElements(by).get(index);
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.clear();
        webElement.sendKeys(text);
    }

    /**
     * Waits for input box to be present, clears it, and sends text
     *
     * @param by in value
     * @param text in value
     */
    public void sendKeys(By by, String text) {
        WebElement webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
        webElement.clear();
        webElement.sendKeys(text);
    }

    /**
     * Waits for input box to be present, clears it, and sends text
     *
     * @param webElement in value
     * @param text in value
     */
    public void sendKeys(WebElement webElement, String text) {
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.clear();
        webElement.sendKeys(text);
    }

    /**
     * Waits for input box to be present and sends text
     *
     * @param by in value
     * @param key in value
     */
    public void sendKeys(By by, Keys key) {
        WebElement webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
        webElement.sendKeys(key);
    }

    /**
     * Waits for input box to be present and sends text
     *
     * @param webElement in value
     * @param key in value
     */
    public void sendKeys(WebElement webElement, Keys key) {
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(key);
    }
}
