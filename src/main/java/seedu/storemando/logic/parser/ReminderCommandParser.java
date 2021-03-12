package seedu.storemando.logic.parser;

import seedu.storemando.logic.commands.ReminderCommand;
import seedu.storemando.logic.parser.exceptions.ParseException;
import seedu.storemando.model.item.ItemExpiringPredicate;

public class ReminderCommandParser implements Parser<ReminderCommand> {

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
