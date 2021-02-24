package seedu.address.commons.util;

/**
 * A container for different regex expressions to check whether the input is valid or not.
 */
public class RegexUtil {

    // Miscellaneous regex expressions
    public static final String SPECIAL_CHARACTERS = "!#$%&'*+/=?`{|}~^.-";
    // Original regex expression for AB3, for reference purposes
    /*
     * The first character of the address and name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String REGEX_AB3_ADDRESS = "[^\\s].*";
    public static final String REGEX_AB3_NAME = "[\\p{Alnum}][\\p{Alnum} ]*";
    public static final String REGEX_AB3_EMAIL = getAb3EmailRegex();
    public static final String REGEX_AB3_PHONE = "\\d{3,}";
    public static final String REGEX_AB3_TAG = "\\p{Alnum}+";

    /**
     * Gets the regex expression for AB3 email model.
     *
     * @return The regex expression for AB3 email model.
     */
    private static String getAb3EmailRegex() {
        // alphanumeric and special characters
        final String localPartRegex = "^[\\w" + SPECIAL_CHARACTERS + "]+";
        final String domainFirstCharacterRegex = "[^\\W_]"; // alphanumeric characters except underscore
        final String domainMiddleRegex = "[a-zA-Z0-9.-]*"; // alphanumeric, period and hyphen
        final String domainLastCharacterRegex = "[^\\W_]$";
        return localPartRegex + "@"
                + domainFirstCharacterRegex + domainMiddleRegex + domainLastCharacterRegex;
    }
}
