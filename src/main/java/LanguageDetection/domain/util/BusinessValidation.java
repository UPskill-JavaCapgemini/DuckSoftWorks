package LanguageDetection.domain.util;

public final class BusinessValidation {

    private static String onlyNumbersRegex = "-?\\d+(\\.\\d+)?";
    private static String onlySpecialCharactersRegex = "^\\W+$";

    private BusinessValidation(){
        // ensure utility
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
