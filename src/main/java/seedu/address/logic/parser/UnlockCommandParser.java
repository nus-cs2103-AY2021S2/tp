package seedu.address.logic.parser;


import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.UnlockCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UnlockCommandParser implements Parser<UnlockCommand> {

    @Override
    public UnlockCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] splitArgs = trimmedArgs.split("\\s+");
        if (splitArgs.length != 1 || trimmedArgs.length() == 0) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    UnlockCommand.MESSAGE_USAGE));
        }
        return new UnlockCommand(splitArgs[0]);
    }
}
