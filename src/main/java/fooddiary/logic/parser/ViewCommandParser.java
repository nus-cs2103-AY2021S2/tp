package fooddiary.logic.parser;

import fooddiary.commons.core.Messages;
import fooddiary.commons.core.index.Index;
import fooddiary.logic.commands.ViewCommand;
import fooddiary.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewCommand(index);
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(e.getMessage());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE), pe);
        }
    }
}
