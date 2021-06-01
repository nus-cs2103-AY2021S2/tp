package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteProjectCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteProjectCommand object
 */
public class DeleteProjectCommandParser implements Parser<DeleteProjectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteProjectCommand
     * and returns a DeleteProjectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteProjectCommand parse(String args) throws ParseException {
        if (args.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteProjectCommand.MESSAGE_USAGE));
        }

        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteProjectCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX, pe);
        }
    }

}
