package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.storemando.logic.commands.ReminderCommand;
import seedu.storemando.logic.parser.exceptions.ParseException;
import seedu.storemando.model.expirydate.ItemExpiringPredicate;

/**
 * Parses input arguments and creates a new ReminderCommand object
 */
public class ReminderCommandParser implements Parser<ReminderCommand> {

    public static final String DAY_KEYWORD = "day";
    public static final String WEEK_KEYWORD = "week";
    public static final String DAYS_KEYWORD = "days";
    public static final String WEEKS_KEYWORD = "weeks";

    /**
     * Convert the given number and the time unit to the number of days.
     * @param parsedNum The number that is use to covert to days
     * @param timeUnit  The time unit in terms of days and weeks
     * @return The number of days
     * @throws ParseException if the user input does not conform the expected keyword
     */
    private long timeConversion(Long parsedNum, String timeUnit) throws ParseException {
        if (parsedNum == 1 || parsedNum == -1 || parsedNum == 0) {
            if (timeUnit.equalsIgnoreCase(DAY_KEYWORD)) {
                return parsedNum;
            } else if (timeUnit.equalsIgnoreCase(WEEK_KEYWORD)) {
                return parsedNum * 7;
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));
            }
        } else {
            if (timeUnit.equalsIgnoreCase(DAYS_KEYWORD)) {
                return parsedNum;
            } else if (timeUnit.equalsIgnoreCase(WEEKS_KEYWORD)) {
                return parsedNum * 7;
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));
            }
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the ReminderCommand
     * and returns a ReminderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ReminderCommand parse(String args) throws ParseException {
        try {
            String trimmedArgs = args.trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));
            }

            String[] wordsInTrimmedArgs = trimmedArgs.replaceAll("\\s{2,}", " ").split(" ");
            long parsedNum = Long.parseLong(wordsInTrimmedArgs[0]);
            String timeUnit = wordsInTrimmedArgs[1];
            long numOfDaysFromToday = timeConversion(parsedNum, timeUnit);
            return new ReminderCommand(new ItemExpiringPredicate(numOfDaysFromToday));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));
        }
    }
}
