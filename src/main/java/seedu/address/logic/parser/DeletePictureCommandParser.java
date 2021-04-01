package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeletePictureCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeletePictureCommandParser implements Parser {

    /**
     * Parses the given {@code String} of arguments in the context of the DeletePictureCommand
     * and returns a DeletePictureCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public DeletePictureCommand parse(String args) throws ParseException {
        requireNonNull(args);

        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeletePictureCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePictureCommand.MESSAGE_USAGE), pe);
        }
    }
}
