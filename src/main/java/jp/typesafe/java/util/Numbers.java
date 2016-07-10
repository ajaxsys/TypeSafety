package jp.typesafe.java.util;

import java.util.regex.Pattern;

public class Numbers {

    private static Pattern Numeric = Pattern.compile("-?\\d+(\\.\\d+)?");
    private static Pattern Int = Pattern.compile("-?\\d+");

    public static boolean isNumeric(String str) {
        return Numeric.matcher(str).matches();
    }

    public static Optional<Double> parseDouble(String s) {
        return isNumeric(s) ?
            Optional.of(Double.parseDouble(s)):
            Optional.empty();
    }

    public static Optional<Integer> parseInt(String s) {
        return isNumeric(s) ?
            Optional.of(Integer.parseInt(s)):
            Optional.empty();
    }

    public static Optional<Long> parseLong(String s) {
        return isNumeric(s) ?
            Optional.of(Long.parseLong(s)):
            Optional.empty();
    }

    public static boolean isInt(String str) {
        return Int.matcher(str).matches();
    }

}
