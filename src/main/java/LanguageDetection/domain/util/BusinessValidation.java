package LanguageDetection.domain.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class BusinessValidation {

    private static String onlyNumbersRegex = "-?\\d+(\\.\\d+)?";
    private static String onlySpecialCharactersRegex = "^\\W+$";

    private BusinessValidation(){
        // ensure utility
    }

    public static void isEmail(final String arg, final String msg) {
        final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
                .compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(arg);
        if(!matcher.find())
            throw new IllegalArgumentException(msg);
    }

    public static void inRange(final int arg, final int min, final int max, final String msg) {
        if(arg<min || arg>max)
            throw new IllegalArgumentException(msg);
    }


    public static boolean isOnlyNumbers(String arg) {
        if (arg.matches(onlyNumbersRegex)){
            return true;
        }
        return false;
    }

    public static boolean isOnlySpecialCharacters(String arg) {
        if (arg.matches(onlySpecialCharactersRegex)){
            return true;
        }
        return false;
    }


}
