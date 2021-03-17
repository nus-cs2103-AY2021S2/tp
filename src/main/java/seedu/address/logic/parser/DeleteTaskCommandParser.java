package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
//@@author mesyeux
import seedu.address.logic.commands.DeleteTaskCommand;
//@@author
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteTaskCommand object
 */
//@@author mesyeux
public class DeleteTaskCommandParser implements Parser<DeleteTaskCommand> {
    //@@author

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTaskCommand
     * and returns a DeleteTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    //@@author mesyeux
    public DeleteTaskCommand parse(String args) throws ParseException {
        //@@author
        try {
            Index index = ParserUtil.parseIndex(args);
            //@@author mesyeux
            return new DeleteTaskCommand(index);
            //@@author
        } catch (ParseException pe) {
            throw new ParseException(
                    //@@author mesyeux
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE), pe);
            //@@author
        }
    }

}
