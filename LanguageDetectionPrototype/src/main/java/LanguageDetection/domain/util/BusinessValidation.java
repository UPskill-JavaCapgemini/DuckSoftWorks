package LanguageDetection.domain.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class BusinessValidation {
    private BusinessValidation(){
        // ensure utility
    }

    public static void nonEmpty(final String arg, final String msg) {
        if(arg==null || arg.isEmpty())
            throw new IllegalArgumentException(msg);
    }

    public static void isEmail(final String arg, final String msg) {
        final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
                .compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(arg);
        if(!matcher.find())
            throw new IllegalArgumentException(msg);
    }

    public static void nonValid(final String arg, final String pattern, final String msg) {
        final Pattern thePatern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        final Matcher matcher = thePatern.matcher(arg);
        if(!matcher.find())
            throw new IllegalArgumentException(msg);
    }

    public static void inRange(final int arg, final int min, final int max, final String msg) {
        if(arg<min || arg>max)
            throw new IllegalArgumentException(msg);
    }

    public static void nonNegative(final double arg,  final String msg) {
        if(arg<0)
            throw new IllegalArgumentException(msg);
    }
}
