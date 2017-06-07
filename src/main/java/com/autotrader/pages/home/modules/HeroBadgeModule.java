package com.autotrader.pages.home.modules;

import com.autotrader.utilities.StableActions;

import com.autotrader.utilities.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by nghiatrongtran on 06/07/2017.
 */
public class HeroBadgeModule {

    //~ Static fields/initializers -------------------------------------------------------------------------------------

    private static final By SELBX_MAKE = By.cssSelector("[data-qaid='selbx-make-hp']");
    private static final By SELBX_MODEL = By.cssSelector("[data-qaid='selbx-model-hp']");
    private static final By SELBX_PRICE = By.cssSelector("[data-qaid='selbx-price-hp']");

    //~ Instance fields ------------------------------------------------------------------------------------------------

    public WebDriver driver;
    public StableActions stableActions;

    //~ Constructors ---------------------------------------------------------------------------------------------------

    /**
     * Creates a new HeroBadgeModule object.
     *
     * @param driver in value
     */
    public HeroBadgeModule(WebDriver driver) {
        this.driver = driver;
        this.stableActions = new StableActions(driver);
    }

    //~ Methods --------------------------------------------------------------------------------------------------------

    public String getMakeSelectedDropdownText() {
        return stableActions.getSelectedTextFromDropDown(SELBX_MAKE);
    }

    public String getModelSelectedDropdownText() {
        return stableActions.getSelectedTextFromDropDown(SELBX_MODEL);
    }

    public String getPriceSelectedDropdownText() {
        return stableActions.getSelectedTextFromDropDown(SELBX_PRICE);
    }

    /**
     * TODO: Enter Javadoc
     *
     * @param text in value
     */
    public void selectMakeDropdown(String text) {
        stableActions.selectElementFromDropDownByText(SELBX_MAKE, text);
        WaitFor.waitForExpectedConditions(driver, SELBX_MODEL);
    }

    /**
     * TODO: Enter Javadoc
     *
     * @param text in value
     */
    public void selectModelDropdown(String text) {
        stableActions.selectElementFromDropDownByText(SELBX_MODEL, text);
    }

    /**
     * TODO: Enter Javadoc
     *
     * @param text in value
     */
    public void selectPriceDropdown(String text) {
        stableActions.selectElementFromDropDownByText(SELBX_PRICE, text);
    }
}
