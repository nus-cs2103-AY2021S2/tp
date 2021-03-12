package seedu.storemando.logic.parser;

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
            throw new ParseException("Empty arguments given.");
        }

        String[] indexes = trimmedArgs.split(" ");
        System.out.println(indexes.length);
        if (indexes.length > 1) {
            throw new ParseException("There is more than 1 arguments given.");
        }

        try {
            Long x = Long.parseLong(indexes[0]);
            return new ReminderCommand(new ItemExpiringPredicate(x));
        } catch (NumberFormatException e) {
            throw new ParseException("Argument is not an integer.");
        }
    }
}
