package com.autotrader.utilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Set;

/**
 * add, delete, get or update a cookie in a test
 */
public final class CookieUtility {

    //~ Static fields/initializers -------------------------------------------------------------------------------------

    private static final Log LOGGER = LogFactory.getLog(CookieUtility.class.getName());

    //~ Enums ----------------------------------------------------------------------------------------------------------

    /**
     * Enumeration for all the CookieNames
     */
    public enum CookieNames {

        //~ Enum constants ---------------------------------------------------------------------------------------------

        ATC_BTC,
        ATC_ID,
        ATC_PID,
        ATC_SID,
        ATC_USER_RADIUS,
        ATC_USER_ZIP,
        BLOCK_TARGETED_OFFER,
        CBRND,
        EXP,
        JSESSIONID,
        MYATC_S1,
        MYAUTOTRADER
    }

    //~ Instance fields ------------------------------------------------------------------------------------------------

    private WebDriver driver;

    //~ Constructors ---------------------------------------------------------------------------------------------------

    /**
     * Creates a new CookieUtility object.
     *
     * @param driver in value
     */
    public CookieUtility(WebDriver driver) {
        this.driver = driver;
    }

    //~ Methods --------------------------------------------------------------------------------------------------------

    /**
     * Return the value stored in a cookie
     *
     * @param cookieName in value
     *
     * @return out value
     */
    public String getCookieValue(CookieNames cookieName) {
        Set<Cookie> allCookies = driver.manage().getCookies();
        boolean doesCookieExist = false;
        String cookieValue = "";
        for (Cookie cookie : allCookies) {
            if (cookie.getName().contains(cookieName.toString())) {
                doesCookieExist = true;
                cookieValue = driver.manage().getCookieNamed(cookieName.toString()).getValue();
            }
        }
        if (!doesCookieExist) {
            LOGGER.error(cookieName + " Cookie doesn't exist!");
        }
        return cookieValue;
    }

    /**
     * Adds a cookie with a given value
     *
     * @param cookieName in value
     * @param value in value
     */
    public void addCookie(CookieNames cookieName, String value) {
        Set<Cookie> allCookies = driver.manage().getCookies();
        for (Cookie cookie : allCookies) {
            if (cookie.getName().contains(cookieName.toString())) {
                if (!cookie.getValue().equals(value)) {
                    LOGGER.info("Updating cookie " + cookieName + " value from " + cookie.getValue() + " to " + value);
                    updateCookieValue(cookieName, value);
                }
                return;
            }
        }
        Cookie cookie = new Cookie(cookieName.toString(), value);
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
    }

    /**
     * Deletes a cookie
     *
     * @param cookieName in value
     */
    public void deleteCookie(CookieNames cookieName) {
        String cookieString = cookieName.toString();

        if (driver.manage().getCookieNamed(cookieString) != null) {
            driver.manage().deleteCookieNamed(cookieString);
            LOGGER.info(cookieString + " cookie deleted");
        } else {
            LOGGER.error(cookieString + " cookie did not exist.");
        }
    }

    /**
     * Find cookie by name and replace it with new value
     *
     * @param cookieName in value
     * @param value in value
     */
    public void updateCookieValue(CookieNames cookieName, String value) {
        Set<Cookie> allCookies = driver.manage().getCookies();
        for (Cookie cookie : allCookies) {
            if (cookie.getName().contains(cookieName.toString())) {
                driver.manage().deleteCookieNamed(cookieName.toString());
                break;
            }
        }
        Cookie cookie = new Cookie(cookieName.toString(), value);
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
    }
}
