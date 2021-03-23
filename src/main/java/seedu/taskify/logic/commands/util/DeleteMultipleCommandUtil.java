package seedu.taskify.logic.commands.util;

import static seedu.taskify.commons.util.StringUtil.reduceWhitespaces;
import static seedu.taskify.model.task.Status.INVALID_STATUS_STRING;
import static seedu.taskify.model.task.Status.isValidStatus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import seedu.taskify.commons.util.StringUtil;
import seedu.taskify.logic.parser.exceptions.ParseException;

/**
 * Utility class for deleting multiple tasks at once
 */
public class DeleteMultipleCommandUtil {


    public static final String MESSAGE_PARSE_MULTIPLE_INDEX_ON_SINGLE_INDEX = "The string passed to ParserUtil"
            + ".parseMultipleIndex() contains only one argument";
    public static final String MESSAGE_AT_LEAST_ONE_INVALID_INDEX = "At least one Index is not a non-zero unsigned "
            + "integer.";
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_INDEX_RANGE = "Invalid index range given. Second index should be "
            + "bigger than the first index.";

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
     * Checks if user is trying to delete all tasks of a specific {@code Status}, from the user's input
     * @param argumentInput user's input other than the command word
     * @throws ParseException if the user did not enter the status correctly.
     */
    public static boolean isDeletingTasksByStatus(String argumentInput) throws ParseException {
        boolean isTrying = argumentInput.contains(" -all");
        if (argumentInput.contains(" all")) {
            throw new ParseException(INVALID_STATUS_STRING);
        }
        if (!isTrying) {
            return false;
        }
        argumentInput = reduceWhitespaces(argumentInput);
        int endIndex = argumentInput.indexOf(" -all");
        String statusArg = argumentInput.substring(0, endIndex);

        if (!isValidStatus(statusArg)) {
            throw new ParseException(INVALID_STATUS_STRING);
        }

        return true;
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
                if (!StringUtil.isNonZeroUnsignedInteger(argument)) {
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
}
