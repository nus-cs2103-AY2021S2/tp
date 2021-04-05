package seedu.address.commons.util;

import java.util.regex.Pattern;

public class Rfc6530Util {
    //@@author user557597
    //Reused from https://stackoverflow.com/questions/56612022/where-can-i-find-a-java-regular-expression-for-email-validation-of-foreign-chara
    public static final Pattern EMAIL_PATTERN = Pattern.compile(
        "(?im)^(?=.{1,64}@)(?:(\"[^\"\\\\]*(?:\\\\.[^\"\\\\]*)*\"@)|((?:[\\pL\\pN](?:\\.(?!\\.)"
            + "|[-!#\\$%&'\\*\\+/=\\?\\^`\\{\\}\\|~\\w])*)?[\\pL\\pN]@))(?=.{1,255}$)(?:(\\[(?:\\d{1,3}\\.){3}\\d{1,"
            + "3}\\])|((?:(?=.{1,63}\\.)[\\pL\\pN][-\\w]*[\\pL\\pN]*\\.)+[\\pL\\pN](?:[\\pL\\pN]|-){0,22}[\\pL\\pN])|"
            + "((?=.{1,63}$)[\\pL\\pN][-\\w]*))$");
}
