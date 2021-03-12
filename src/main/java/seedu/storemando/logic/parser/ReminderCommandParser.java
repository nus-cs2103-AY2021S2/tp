package seedu.storemando.logic.parser;

import seedu.storemando.logic.commands.ReminderCommand;
import seedu.storemando.logic.parser.exceptions.ParseException;

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

        return new ReminderCommand(x->true);
    }
}
