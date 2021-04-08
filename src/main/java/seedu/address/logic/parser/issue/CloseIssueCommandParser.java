package seedu.address.logic.parser.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.issue.CloseIssueCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CloseIssueCommand object
 */
public class CloseIssueCommandParser implements Parser<CloseIssueCommand> {

    private final Logger logger = LogsCenter.getLogger(CloseIssueCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the CloseIssueCommand
     * and returns a CloseIssueCommand object for execution.
     *
     * @param args the argument string
     * @return {@code CloseIssueCommand} with the specified arguments
     * @throws ParseException       if the user input does not conform the expected format
     * @throws NullPointerException if args is null
     */
    public CloseIssueCommand parse(String args) throws ParseException {
        requireNonNull(args);

        try {
            Index index = ParserUtil.parseIndex(args);
            return new CloseIssueCommand(index);
        } catch (ParseException pe) {
            logger.warning("Failed to parse index for iclo command");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CloseIssueCommand.MESSAGE_USAGE), pe);
        }
    }

}
