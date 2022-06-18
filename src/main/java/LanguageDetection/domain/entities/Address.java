package LanguageDetection.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EqualsAndHashCode
@ToString
public class Address {
    @Getter
    private String street;
    @Getter
    private String city;
    @Getter
    private String postalCode;
    @Getter
    private String countryCode;

    public Address(String street, String city, String postalCode, String countryCode) {
        this.street = setValidStreet(street);
        this.city = setValidCity(city);
        this.postalCode = setValidPostalCode(postalCode);
        this.countryCode = countryCode;
    }

    /**
     * Private set for City: Validate if City Name is not a number, null or it's missing
     *
     * @param city
     */
    private String setValidCity(String city) {
        if (isNumeric(city) || city == null || city.isEmpty()) {
            throw new IllegalArgumentException("The city in your Address is not valid or it's missing. Please try again.");
        } else {
            return city.toUpperCase();
        }
    }

    /**
     * Private set for Street: Validate if Street is not a number, null or it's missing
     *
     * @param street
     */
    public String setValidStreet(String street) {
        if (street == null || street.isEmpty()) {
            throw new IllegalArgumentException("The street format in your Address is not valid or it's missing. Please try again");
        } else return street.toUpperCase();
    }

    private String setValidPostalCode(String postalCode) {
        if (postalCode == null || postalCode.isEmpty())
            throw new IllegalArgumentException("Postal Code can´t be null! (Correct Format: xxxx-xxx)");
        else {
            if (postalCode.length() == 7) {
                postalCode = addHyphenToPostalCode(postalCode);
                return postalCode;
            }
            //Validates if the zip code is in the correct format (4620-580) - PT Format
            if (postalCodeIsInCorrectFormat(postalCode)) {
                return postalCode;
            } else {
                throw new IllegalArgumentException("Postal Code is not in the correct format! (xxxx-xxx)");
            }
        }
    }

    /**
     * Auxiliary method to Validate if the postal code is in the correct format (4620-580) - Validation for PT users
     *
     * @param postalCode
     * @return
     */

    private boolean postalCodeIsInCorrectFormat(String postalCode) {
        String regex = "^[0-9]{4}-[0-9]{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(postalCode);
        return matcher.matches();
    }

    /**
     * Auxiliary method to Add '-' in case user forget to add it
     *
     * @param postalCode
     * @return
     */

    private static String addHyphenToPostalCode(String postalCode) {
        return postalCode.substring(0, 4) + "-" + postalCode.substring(4, postalCode.length());
    }

    /**
     * Validate if City is not Numeric
     *
     * @param city
     * @return
     */

    private static boolean isNumeric(String city) {
        if (city != null)
            for (char c : city.toCharArray()) {
                if (Character.isDigit(c))
                    return true;
            }
        return false;
    }
}
