package com.elearning.elearning.exception.Response;

public class Message {
    /**
     *   PATTERN
     */
    public static final String NUMBER = "^[0-9]+";
    public static final String MAIL = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$";
    public static final String ALPHANUMBER = "^[A-Za-z0-9]+";
    public static final String ALPHA = "^[A-Za-z]+";

    public static String textFormat(final String value) {
        return String.format("le Format du %s F-CFA est in correcte", value);
    }
}
