package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     * Ignores case, but a full word match is required.
     * <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     *
     * @param sentence cannot be null
     * @param word     cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Returns true if the {@code sentence} contains the prefix {@code word}.
     * Ignores case.
     * <br>examples:<pre>
     *       containsPrefixWordIgnoreCase("ABc def", "abc") == true
     *       containsPrefixWordIgnoreCase("ABc def", "DEF") == true
     *       containsPrefixWordIgnoreCase("ABc def", "AB") == true
     *       containsPrefixWordIgnoreCase("CAB def", "AB") == false
     *       </pre>
     *
     * @param sentence cannot be null
     * @param word     cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsPrefixWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim().toLowerCase();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        return sentence.toLowerCase().matches(".*\\b" + preppedWord + ".*");
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     *
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Splits an input keywords string by empty spaces into a list of tokens. <br>
     * Examples:
     * <pre>
     *      splitToKeywordsList("gouda") == ["gouda"]
     *      splitToKeywordsList("gouda brie") == ["gouda", "brie"]
     *      splitToKeywordsList("") == []
     * </pre>
     */
    public static List<String> splitToKeywordsList(String keywords) {
        return keywords.isEmpty() ? Collections.emptyList() : Arrays.asList(keywords.trim().split("\\s+"));
    }

    /**
     * Replaces all special characters in emails with empty spaces.
     * Used in find methods to extract essential keywords from emails (separated by empty space).
     */
    public static String replaceEmailSpecialCharacters(String email) {
        requireNonNull(email);
        return email.replaceAll("[@\\.]+", " ").trim();
    }

    /**
     * Converts a string to title case. <br>
     * Examples:
     * <pre>
     *      convertToTitleCase("BRIE") == "Brie"
     *      convertToTitleCase("gouda") == "Gouda"
     *      convertToTitleCase("blue cheese") == "Blue Cheese"
     * </pre>
     */
    public static String convertToTitleCase(String s) {
        requireNonNull(s);

        StringBuilder result = new StringBuilder();
        List<String> wordsList = splitToKeywordsList(s);

        for (String str : wordsList) {
            for (int i = 0; i < str.length(); i++) {
                result.append(i == 0 ? Character.toUpperCase(str.charAt(i)) : Character.toLowerCase(str.charAt(i)));
            }
            result.append(" ");
        }

        return result.toString().trim();
    }

}
