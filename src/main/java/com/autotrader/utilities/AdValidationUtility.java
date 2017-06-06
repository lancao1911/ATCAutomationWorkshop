package com.autotrader.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This Utility should be used when testing Ads
 */
public class AdValidationUtility {

    //~ Enums ----------------------------------------------------------------------------------------------------------

    /**
     * List of Ad Parameters
     */
    public enum AdParameter {

        //~ Enum constants ---------------------------------------------------------------------------------------------

        ID("id"),
        INLINE("inline"),
        LABEL_MARGIN("labelMargin"),
        POSITION("position"),
        PRODUCT_KEY("productKey"),
        RAISE_LABEL("raiseLabel"),
        SHOW_LABEL("showLabel"),
        SIZES("sizes");

        //~ Instance fields --------------------------------------------------------------------------------------------

        private String parameter;

        //~ Constructors -----------------------------------------------------------------------------------------------

        /**
         * Creates a new FeatureFlag object.
         *
         * @param parameter in value
         */
        AdParameter(String parameter) {
            this.parameter = parameter;
        }

        //~ Methods ----------------------------------------------------------------------------------------------------

        /**
         * Returns the variant value of the feature flag enum in correct case
         *
         * @return out value
         */
        @Override
        public String toString() {
            return parameter;
        }
    }

    //~ Instance fields ------------------------------------------------------------------------------------------------

    private WebDriver driver;

    //~ Constructors ---------------------------------------------------------------------------------------------------

    /**
     * Creates a new AdValidationUtility object.
     *
     * @param driver in value
     */
    public AdValidationUtility(WebDriver driver) {
        this.driver = driver;
    }

    //~ Methods --------------------------------------------------------------------------------------------------------

    public String getAdParameterValue(By ad, AdParameter adParameter) {
        List<List<String>> parametersList = new ArrayList<>();
        String[] data =
            driver.findElement(ad).getAttribute("data-qa-data").replace("{", "").replace("}", "").split(",\"");
        for (String parameter : data) {
            String[] parameters = parameter.replace("\"", "").split(":");
            parametersList.add(Arrays.asList(parameters[0], parameters[1]));
        }
        for (List<String> parameters : parametersList) {
            if (adParameter.toString().equals(parameters.get(0))) {
                return parameters.get(1);
            }
        }
        return null;
    }
}
