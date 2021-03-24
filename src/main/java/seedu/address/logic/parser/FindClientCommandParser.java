package seedu.address.logic.parser;

import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.commands.FindClientCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class FindClientCommandParser implements Parser<FindClientCommand> {

    @Override
    public FindClientCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
        }

        return null;
    }
}
