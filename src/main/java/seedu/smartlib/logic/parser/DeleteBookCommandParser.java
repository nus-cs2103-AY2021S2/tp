package seedu.smartlib.logic.parser;

import static seedu.smartlib.commons.core.Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX;
import static seedu.smartlib.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.smartlib.commons.core.index.Index;
import seedu.smartlib.logic.commands.DeleteBookCommand;
import seedu.smartlib.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteBookCommand object.
 */
public class DeleteBookCommandParser implements Parser<DeleteBookCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteBookCommand
     * and returns a DeleteBookCommand object for execution.
     *
     * @param args arguments given in the user input.
     * @return a DeleteBookCommand object required for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteBookCommand parse(String args) throws ParseException {

        if (!args.trim().matches("^[a-zA-Z]*$")) {
            try {
                Integer.parseInt(args.trim());
            } catch (NumberFormatException ne) {
                throw new ParseException(MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
            }
        }

        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteBookCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBookCommand.MESSAGE_USAGE), pe);
        }
    }

}

