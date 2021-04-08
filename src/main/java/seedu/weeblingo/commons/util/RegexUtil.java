package seedu.weeblingo.commons.util;

/**
 * A container for different regex expressions to check whether the input is valid or not.
 */
public class RegexUtil {

    // Miscellaneous regex expressions
    public static final String SPECIAL_CHARACTERS = "!#$%&'*+/=?`{|}~^.-";

    // Regex expressions that might be useful in project Weeblingo
    // The first character of the string to check cannot be white space
    public static final String REGEX_JAP_WORD =
            "[(\\p{InHIRAGANA}|\\p{InKATAKANA})"
            + "|\\p{InCJK_Unified_Ideographs}}]"
            + "[(\\p{InHIRAGANA}|\\p{InKATAKANA})"
            + "|\\p{InCJK_Unified_Ideographs}}]*";
    public static final String REGEX_JAP_SENTENCE =
            "[(\\p{InHIRAGANA}|\\p{InKATAKANA})|\\p{InCJK_Unified_Ideographs}|"
            + "\\p{InCJK_Symbols_and_Punctuation}|\\p{Alnum}|\\p{Punct}]"
            + "[(\\p{InHIRAGANA}|\\p{InKATAKANA})|\\p{InCJK_Unified_Ideographs}|"
                    + "\\p{InCJK_Symbols_and_Punctuation}|\\p{Alnum}|\\p{Punct} ]*";
    public static final String REGEX_ENG_WORDS = "[\\p{Alpha}][\\p{Alpha} ]*";
    public static final String REGEX_ENG_SENTENCE = "[\\p{Alnum}|\\p{Punct}]"
            + "[\\p{Alnum}|\\p{Punct} ]*";
    public static final String REGEX_WEEBLINGO_TAG = "[\\p{Alnum}][\\p{Alnum} ]*";

}
