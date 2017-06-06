package com.autotrader.utilities;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by jmmaynard on 4/5/2017.<br>
 * Utility methods to validate the format of various inputs, such as ListingIDs, VINs, phone numbers, etc.
 */
public final class FormatValidationUtility {

    /**
     * Private constructor to enforce only having static access to static methods
     */
    private FormatValidationUtility() {
    }

    /**
     * Checks that the input string is in the format of a valid listing ID - only digits 0-9, at least 8 digits and no
     * more than 10
     *
     * @param input in value
     *
     * @return out value
     */
    public static boolean validateListingIdFormat(String input) {
        final Pattern pattern = Pattern.compile("^[0-9]{8,10}$");
        return StringUtils.isNotBlank(input) && pattern.matcher(input).matches();
    }

    /**
     * Checks that the input string is ROUGHLY in the format of a valid Vehicle Identification Number - 17 Characters
     * exactly - Numbers and letters, EXCLUDING 'o', 'i', or 'q' - DOES NOT CHECK THAT FOR STRICT VALIDITY OF POSITION
     * OF SPECIFIC LETTERS IN THE STRING
     *
     * @param input in value
     *
     * @return out value
     */
    public static boolean validateVinFormat(String input) {
        final Pattern pattern = Pattern.compile("^[A-HJ-NPR-Za-hj-npr-z\\d]{17}$");
        return StringUtils.isNotBlank(input) && pattern.matcher(input).matches();
    }

}
