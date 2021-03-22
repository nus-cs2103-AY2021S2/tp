package seedu.address.logic.parser;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_INSURANCE_POLICY = new Prefix("i/");
    public static final Prefix PREFIX_INSURANCE_POLICY_URL = new Prefix("u/");
    public static final Prefix PREFIX_MEETING = new Prefix("m/");

    public static final Prefix PREFIX_LIST_POLICY = new Prefix("-policy");

    /**
     * Checks if a given string is a valid prefix.
     * @param input the string to validate.
     * @return true if the string is a valid prefix.
     */
    public static boolean isValidPrefix(String input) {
        HashSet<Prefix> prefixes = new HashSet<>(Arrays.asList(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_TAG, PREFIX_INSURANCE_POLICY, PREFIX_INSURANCE_POLICY_URL, PREFIX_MEETING));
        return prefixes.contains(new Prefix(input));
    }

}
