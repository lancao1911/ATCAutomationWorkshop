package com.autotrader.consumersite;

import com.autotrader.BaseUrlManager;
import org.openqa.selenium.WebDriver;

import java.net.URL;

/**
 * Created by nghiatrongtran on 05/22/2017.
 */
public class FeatureFlagManager {

    public enum FeatureFlag {

        // Email Seller small confirmation modal
        ES_CONF_MOD("esConfMod"),

        OLD_HP("na"),

        // My Cars Counter
        MY_CARS_COUNTER("myCarsCounter"),

        // Quick Save
        QUICK_SAVE("quickSave"),

        // Header on react search in progress page
        SIPHEADER("sipHeader"),

        // React search in progress page
        SIPREACT("sipReact"),

        // Speedy search form
        SPDY_ASF("spdyASF"),

        // Speedy filter optimization
        SPDY_FO("spdyFO"),

        // Speedy filter optimization truck cab
        SPDY_FO_TRUCK("spdyFOTruck"),

        // Speedy homepage
        SPDY_HP("spdyHP"),

        // 3 column speedy vdp
        SPEEDY_VDP("speedyVDP"),

        // 2 column speedy vdp
        SPEEDY_VDP_TWO_COLUMN("SpeedyVDPTwoColumn"),

        // speedy srp
        USE_REACT("useReact"),

        // Rest easy SRP
        USE_RST_SRP("useRstSRP");

        private String variant;

        FeatureFlag(String variant){this.variant = variant;}

        @Override
        public String toString(){return variant;}
    }

    private URL baseUrl;
    private WebDriver webDriver;
    private String globalFeatureFlag;

    FeatureFlagManager(WebDriver webDriver){
        this.webDriver = webDriver;
        this.baseUrl = BaseUrlManager.getBaseUrl();
    }

    void setFeatureFlag(FeatureFlag... featureFlags) {
        if (featureFlags.length != 0 || globalFeatureFlag != null) {
            StringBuilder bld = new StringBuilder();
            bld.append(baseUrl);
            bld.append("rest/test/setExperiment?forceFeatureFlag=");
            if (featureFlags.length != 0) {
                for (Enum setFeatureFlag : featureFlags) {
                    bld.append(setFeatureFlag.toString());
                    bld.append("!");
                }
            }
            if (globalFeatureFlag != null) {
                bld.append(globalFeatureFlag);
            } else {
                bld.deleteCharAt(bld.length() - 1);
            }
            webDriver.get(bld.toString());
        }
    }

    void setGlobalFeatureFlag(String globalFeatureFlag) {
        this.globalFeatureFlag = globalFeatureFlag;
    }
}
