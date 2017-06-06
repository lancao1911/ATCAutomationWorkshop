package com.autotrader.consumersite.dataisland;

import com.autotrader.ParseDataIsland;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by nghiatrongtran on 05/23/2017.
 */
public class ReactDataIsland implements DataIsland {

    private ReactDataIsland() {
    }

    public static String getBirfAttributeValue(WebDriver driver, DataIsland.BirfAttribute attribute) {
        return new ParseDataIsland(driver.findElement(By.cssSelector("[data-birf-role='dataisland']")).getAttribute(
                "data-birf-extra")).getBirfAttribute(attribute.getAttribute());
    }
}
