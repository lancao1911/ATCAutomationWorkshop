package com.autotrader.consumersite.dataisland;

import com.autotrader.ParseDataIsland;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This gets Birf attributes from the Data Island On Chassis Pages
 */
public class ChassisDataIsland implements DataIsland {

    //~ Constructors ---------------------------------------------------------------------------------------------------

    /**
     * Creates a new ChassisDataIsland object.
     */
    private ChassisDataIsland() {
    }

    //~ Methods --------------------------------------------------------------------------------------------------------

    public static String getBirfAttributeValue(WebDriver driver, BirfAttribute attribute) {
        return new ParseDataIsland(driver.findElement(By.xpath("//script[contains(.,'BIRF.extend')]")).getAttribute(
                    "text")).getBirfAttribute(attribute.getAttribute());
    }
}
