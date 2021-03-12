package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.storemando.logic.commands.ReminderCommand;
import seedu.storemando.logic.parser.exceptions.ParseException;
import seedu.storemando.model.item.ItemExpiringPredicate;

/**
 * Parses input arguments and creates a new ReminderCommand object
 */
public class ReminderCommandParser implements Parser<ReminderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ReminderCommand
     * and returns a ReminderCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ReminderCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));
        }

        String[] numOfArgs = trimmedArgs.split(" ");
        if (numOfArgs.length > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));
        }

        try {
            Long numOfDayFromToday = Long.parseLong(numOfArgs[0]);
            return new ReminderCommand(new ItemExpiringPredicate(numOfDayFromToday));
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));
        }
    }
}
