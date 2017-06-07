package com.autotrader.consumersite;

import com.autotrader.BaseUrlManager;
import com.autotrader.CapabilityManager;
import com.autotrader.WebDriverManager;
//import com.autotrader.commons.quicksave.QuickSave;
//import com.autotrader.pages.funnels.findyourcar.FindYourCarFunnel;
//import com.autotrader.pages.home.HomePage;
import com.autotrader.pages.home.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.URL;

/**
 * Created by nghiatrongtran on 05/22/2017.
 */
public class ConsumerSite {

    private URL baseUrl;
    private WebDriver webDriver;
    private String webServerNumber;

    public ConsumerSite(FeatureFlagManager.FeatureFlag... featureFlags){
        this.webDriver = WebDriverManager.getDriver();
        this.baseUrl = BaseUrlManager.getBaseUrl();
        webDriver.get(BaseUrlManager.getBaseUrl() + ".whoami");
        webServerNumber = webDriver.findElement(By.tagName("pre")).getText();
        FeatureFlagManager featureFlagManager = new FeatureFlagManager(webDriver);
        Object globalFeatureFlag = CapabilityManager.getCapabilities().getCapability("globalFeatureFlag");
        if (globalFeatureFlag != null){
            featureFlagManager.setGlobalFeatureFlag(globalFeatureFlag.toString());
        }
        featureFlagManager.setFeatureFlag(featureFlags);
    }

    public HomePage getHomePage(){return new HomePage(webDriver, baseUrl);}
}
