package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteIssueCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteIssueCommand object
 */
public class DeleteIssueCommandParser implements Parser<DeleteIssueCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteIssueCommand
     * and returns a DeleteIssueCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteIssueCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteIssueCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteIssueCommand.MESSAGE_USAGE), pe);
        }
    }

}
