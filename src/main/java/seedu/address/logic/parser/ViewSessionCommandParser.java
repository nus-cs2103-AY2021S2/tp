package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ViewSessionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.session.SessionId;
import seedu.address.model.session.SessionIdPredicate;


/**
 * Parses the given {@code String} of arguments in the context of the ViewSessionCommand
 * and returns a ViewSessionCommand object for execution.
 * @throws ParseException if the user input does not conform the expected format
 */
public class ViewSessionCommandParser implements Parser<ViewSessionCommand> {

    /**
     * Parses input arguments and creates a new ViewSessionCommand object
     */
    public ViewSessionCommand parse(String args) throws ParseException {
        try {
            SessionId targetClassId = ParserUtil.parseSessionId(args);
            return new ViewSessionCommand(new SessionIdPredicate(targetClassId));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewSessionCommand.MESSAGE_USAGE), pe);
        }
    }

}
