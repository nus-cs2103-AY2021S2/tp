package seedu.us.among.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.us.among.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
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
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, but a partial word match is required.
     *   <br>examples:<pre>
     *       containsPartialWordIgnoreCase("ABc def", "abc") == true
     *       containsPartialWordIgnoreCase("ABc def", "AB") == true
     *       containsPartialWordIgnoreCase("ABc def", "Ac") == false //not in sequence
     *
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a partial word
     */
    public static boolean containsPartialWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim().toLowerCase();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence.trim().toLowerCase();

        return preppedSentence.contains(word);
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
     * Returns a number to 3 decimal place, in string
     */
    public static String get3DecimalPlace(Double value) {
        requireNonNull(value);
        return String.format("%.03f", value);
    }

    /**
     * Returns a number with seconds following it, in string
     */
    public static String getResponseTimeInString(Double value) {
        requireNonNull(value);
        return String.format("%s seconds", get3DecimalPlace(value));
    }

    //Solution below taken from https://www.javacodeexamples.com/convert-string-to-title-case-in-java-example/641
    /**
     * Returns word in title case.
     *
     * @param str string to return in title case
     * @return string with title case
     */
    public static String toTitleCase(String str) {

        if (str == null || str.isEmpty()) {
            return "";
        }

        if (str.length() == 1) {
            return str.toUpperCase();
        }

        String[] parts = str.split(" ");

        StringBuilder sb = new StringBuilder(str.length());

        for (String part : parts) {
            char[] charArray = part.toLowerCase().toCharArray();
            charArray[0] = Character.toUpperCase(charArray[0]);

            sb.append(new String(charArray)).append(" ");
        }

        return sb.toString().trim();
    }

}
