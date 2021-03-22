package seedu.taskify.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.taskify.commons.core.Messages.MESSAGE_INVALID_INDEX_RANGE;
import static seedu.taskify.commons.util.AppUtil.checkArgument;
import static seedu.taskify.commons.core.Messages.MESSAGE_AT_LEAST_ONE_INVALID_INDEX;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import seedu.taskify.logic.parser.exceptions.ParseException;

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
     * Checks if {@code argumentInput} contains more than one valid index and if all are valid indexes.
     * @param argumentInput user's input excluding the command word
     * @return false if {@code argumentInput} contains only one index, that is valid. Note that the range "x-x" where
     * x is a non zero unsigned integer will throw a ParseException instead
     * @throws ParseException if {@code argumentInput} cannot be parsed properly later on (See {@code
     * extractStringArgumentsIntoIndexes(String input)}).
     */
    public static boolean hasMultipleValidIndex(String argumentInput) throws ParseException {
        String[] arguments = extractStringArgumentsIntoIndexes(argumentInput);
        boolean hasOnlyOneArgument = arguments.length == 1;

        return !hasOnlyOneArgument;
    }

    /**
     * Extracts a String array of indexes if the input follows the format for multiple indexes correctly.
     *
     * @param input String to extract from
     * @return a String array containing the valid or invalid indexes
     * @throws ParseException if an invalid index range was given i.e "2-2" or "100-99", or if an index has leading
     * zeroes i.e "0-1" or "01-09", or if the arguments are incomprehensible.
     */
    public static String[] extractStringArgumentsIntoIndexes(String input) throws ParseException {
        input = reduceWhitespaces(input);

        String regexForRangedArgs = "(?<firstNum>[0-9]+)-(?<secondNum>[0-9]+)(?<remaining>.*)";
        String regexForRemaining = "\\D+"; // if this is matched, then we should reject the input

        Pattern patternRangedArgs = Pattern.compile(regexForRangedArgs);
        Pattern patternRemaining = Pattern.compile(regexForRemaining);

        Matcher matcherRangedArgs = patternRangedArgs.matcher(input);
        boolean hasFoundIndexRange = matcherRangedArgs.find();

        boolean hasNoRemainingInvalidChar;

        if (!hasFoundIndexRange) {
            String[] arguments = input.split(" ");
            for (String argument : arguments) {
                if (!isNonZeroUnsignedInteger(argument)) {
                    throw new ParseException(MESSAGE_AT_LEAST_ONE_INVALID_INDEX);
                }
            }
            return arguments;
        }

        Matcher matcherRemaining = patternRemaining.matcher(matcherRangedArgs.group("remaining"));
        hasNoRemainingInvalidChar = !matcherRemaining.find();

        if (!hasNoRemainingInvalidChar) {
            throw new ParseException(MESSAGE_AT_LEAST_ONE_INVALID_INDEX);
        }

        String first = matcherRangedArgs.group("firstNum");
        String second = matcherRangedArgs.group("secondNum");

        String leadingZeroesRegex = "0+[1-9]*";
        boolean isFirstIndexInvalid = first.matches(leadingZeroesRegex);
        boolean isSecondIndexInvalid = second.matches(leadingZeroesRegex);

        if (isFirstIndexInvalid || isSecondIndexInvalid) {
                /* MESSAGE_INVALID_INDEX_RANGE is not as appropriate as an error message, since it is for indicating
                when the second number is bigger than the first number. In situations such as "0100-99", while it
                the first error caught should be that 0100 is an invalid index, rather than 100 > 99. */
            throw new ParseException(MESSAGE_AT_LEAST_ONE_INVALID_INDEX);
        }

        int firstNum = Integer.parseInt(first);
        int secondNum = Integer.parseInt(second);
        boolean isRangeInvalid = firstNum >= secondNum;

        if (isRangeInvalid) {
            throw new ParseException(MESSAGE_INVALID_INDEX_RANGE);
        }

        return IntStream.rangeClosed(firstNum, secondNum).mapToObj(String::valueOf).toArray(String[]::new);
    }

    /**
     * Trims the input String and reduces all additional whitespace between substrings to just one.
     */
    public static String reduceWhitespaces(String input) {
        input = input.trim();
        input = input.replaceAll("\\s{2,}", " ");
        return input;
    }
}
