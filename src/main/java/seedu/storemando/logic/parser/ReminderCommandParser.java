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

    /**
     * Convert the given number and the time unit to the number of days.
     * @param parsedNum The number that is use to covert to days
     * @param timeUnit The time unit in terms of days and weeks
     * @return The number of days
     * @throws ParseException if the user input does not conform the expected keyword
     */
    private long timeConversion(Long parsedNum, String timeUnit) throws ParseException {
        String timeUnitLowerCase = timeUnit.toLowerCase();

        if (timeUnitLowerCase.substring(0, 3).equals(DAY_KEYWORD)) {
            return parsedNum;
        } else if (timeUnitLowerCase.substring(0, 4).equals(WEEK_KEYWORD)) {
            return parsedNum * 7;
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the ReminderCommand
     * and returns a ReminderCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ReminderCommand parse(String args) throws ParseException {
        try {
            String trimmedArgs = args.trim();
            String[] stringArgsArr = trimmedArgs.replaceAll("\\s{2,}", " ").split(" ");
            long parsedNumber = Long.parseLong(stringArgsArr[0]);
            String timeUnit = stringArgsArr[1];
            long numOfDaysFromToday = timeConversion(parsedNumber, timeUnit);

            if (trimmedArgs.isEmpty() || numOfDaysFromToday <= 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));
            }
            return new ReminderCommand(new ItemExpiringPredicate(numOfDaysFromToday));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));
        }
    }
}
