package seedu.address.logic.parser.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.issue.DeleteIssueCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteIssueCommand object.
 */
public class DeleteIssueCommandParser implements Parser<DeleteIssueCommand> {

    private final Logger logger = LogsCenter.getLogger(DeleteIssueCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteIssueCommand
     * and returns a DeleteIssueCommand object for execution.
     *
     * @param args The argument string.
     * @return {@code DeleteIssueCommand} with the specified arguments.
     * @throws ParseException       If the user input does not conform the expected format.
     * @throws NullPointerException If args is null.
     */
    public DeleteIssueCommand parse(String args) throws ParseException {
        requireNonNull(args);

        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteIssueCommand(index);
        } catch (IllegalArgumentException iex) {
            logger.warning("Failed to parse index for idel command");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteIssueCommand.MESSAGE_USAGE), iex);
        }
    }

}
