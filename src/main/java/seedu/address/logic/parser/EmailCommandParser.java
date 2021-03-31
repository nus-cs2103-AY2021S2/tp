package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.EmailCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class EmailCommandParser implements Parser<EmailCommand> {

    public static final String SPECIAL_INDEX = "shown";

    @Override
    public EmailCommand parse(String args) throws ParseException {
        requireNonNull(args.trim());

        if (args.trim().equals(SPECIAL_INDEX)) {
            return new EmailCommand();
        }

        try {
            return new EmailCommand(ParserUtil.parseIndexes(args));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EmailCommand.MESSAGE_USAGE), pe);
        }
    }

    @Override
    public boolean isValidCommandToAlias(String args) {
        if (args.trim().isEmpty()) {
            return true;
        }

        if (args.trim().equals(SPECIAL_INDEX)) {
            return true;
        }

        try {
            ParserUtil.parseIndexes(args);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }
}
