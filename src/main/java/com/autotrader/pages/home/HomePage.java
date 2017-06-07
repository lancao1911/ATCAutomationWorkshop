package com.autotrader.pages.home;

import com.autotrader.PageObject;
import com.autotrader.consumersite.dataisland.ReactDataIsland;
import com.autotrader.pages.home.modules.HeroBadgeModule;
import org.openqa.selenium.WebDriver;

import java.net.URL;

/**
 * Created by nghiatrongtran on 06/07/2017.
 */
public class HomePage implements PageObject {

    private static final String PAGE_ID = "atc_h";

    public WebDriver driver;
    public URL baseUrl;

    public HomePage(WebDriver driver, URL baseUrl){
        this.driver = driver;
        this.baseUrl = baseUrl;
    }

    @Override
    public boolean isCurrentPage(){
        return PAGE_ID.equals(ReactDataIsland.getBirfAttributeValue(driver, ReactDataIsland.BirfAttribute.PAGE_ID));
    }

    public HeroBadgeModule getHeroBadgeModule() {return new HeroBadgeModule(driver);}

    public void navigateTo(){
        driver.get(baseUrl.toString());
    }
}
