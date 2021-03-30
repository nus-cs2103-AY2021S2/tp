package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.EmailCommand.MESSAGE_USAGE;

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
        } catch (ParseException ex) {
            throw new ParseException(MESSAGE_USAGE);
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
