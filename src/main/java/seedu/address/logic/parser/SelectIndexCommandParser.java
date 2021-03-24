package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.SelectCommand.MESSAGE_USAGE;

import seedu.address.logic.commands.SelectIndexCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SelectIndexCommand object.
 */
public class SelectIndexCommandParser implements Parser<SelectIndexCommand> {

    public static final String SPECIAL_INDEX = "shown";

    @Override
    public SelectIndexCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (args.equals(SPECIAL_INDEX)) {
            return new SelectIndexCommand();
        }

        try {
            return new SelectIndexCommand(ParserUtil.parseIndexes(args));
        } catch (ParseException ex) {
            throw new ParseException(MESSAGE_USAGE);
        }

    }

    @Override
    public boolean isValidCommandToAlias(String args) {
        try {
            parse(args);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
