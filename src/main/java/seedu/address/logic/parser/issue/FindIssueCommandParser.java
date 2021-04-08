package seedu.address.logic.parser.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.issue.FindIssueCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.issue.RoomNumberOrTagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindIssueCommand object
 */
public class FindIssueCommandParser implements Parser<FindIssueCommand> {

    private final Logger logger = LogsCenter.getLogger(FindIssueCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the FindIssueCommand
     * and returns a FindIssueCommand object for execution.
     *
     * @param args The argument string.
     * @return {@code FindIssueCommand} with the specified arguments.
     * @throws ParseException       If the user input does not conform the expected format.
     * @throws NullPointerException If args is null.
     */
    public FindIssueCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            logger.warning("ifind was either given an empty keyword or a keyword with only whitespaces");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindIssueCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindIssueCommand(new RoomNumberOrTagContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
