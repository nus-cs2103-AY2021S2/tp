package seedu.address.logic.parser;

import java.util.StringJoiner;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    public static final String PREFIX_NAME_STRING = "n/";
    public static final String PREFIX_PHONE_STRING = "p/";
    public static final String PREFIX_ADDRESS_STRING = "a/";
    public static final String PREFIX_TAG_STRING = "tag/";
    public static final String PREFIX_ALL_STRING = "all/";
    public static final String PREFIX_TRIPTIME_STRING = "t/";
    public static final String PREFIX_TRIPDAY_STRING = "d/";
    public static final String PREFIX_PRICE_STRING = "pr/";
    public static final String PREFIX_COMMUTER_STRING = "c/";

    public static final String PREFIX_STRING_LIST = joinPrefixes();

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix(PREFIX_NAME_STRING);
    public static final Prefix PREFIX_PHONE = new Prefix(PREFIX_PHONE_STRING);
    public static final Prefix PREFIX_ADDRESS = new Prefix(PREFIX_ADDRESS_STRING);
    public static final Prefix PREFIX_TAG = new Prefix(PREFIX_TAG_STRING);
    public static final Prefix PREFIX_TRIPDAY = new Prefix(PREFIX_TRIPDAY_STRING);
    public static final Prefix PREFIX_TRIPTIME = new Prefix(PREFIX_TRIPTIME_STRING);
    public static final Prefix PREFIX_COMMUTER = new Prefix(PREFIX_COMMUTER_STRING);
    public static final Prefix PREFIX_PRICE = new Prefix(PREFIX_PRICE_STRING);
    public static final Prefix PREFIX_ALL = new Prefix(PREFIX_ALL_STRING);

    /**
     * Prevents CliSyntax from being instantiated.
     */
    private CliSyntax() {}

    private static String joinPrefixes() {
        StringJoiner sj = new StringJoiner(", ");
        sj.add(PREFIX_NAME_STRING);
        sj.add(PREFIX_PHONE_STRING);
        sj.add(PREFIX_ADDRESS_STRING);
        sj.add(PREFIX_TAG_STRING);
        sj.add(PREFIX_ALL_STRING);
        sj.add(PREFIX_TRIPTIME_STRING);
        sj.add(PREFIX_TRIPDAY_STRING);
        sj.add(PREFIX_PRICE_STRING);
        sj.add(PREFIX_COMMUTER_STRING);

        return sj.toString();
    }
}
