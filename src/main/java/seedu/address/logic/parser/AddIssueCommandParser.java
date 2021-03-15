package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESTAMP;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddIssueCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.issue.Category;
import seedu.address.model.issue.Description;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueStatus;
import seedu.address.model.issue.RoomNumber;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Timestamp;

/**
 * Parses input arguments and creates a new AddIssueCommand object
 */
public class AddIssueCommandParser implements Parser<AddIssueCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddIssueCommand
     * and returns an AddIssueCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddIssueCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ROOM_NO, PREFIX_DESCRIPTION,
                PREFIX_TIMESTAMP, PREFIX_STATUS, PREFIX_CATEGORY);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROOM_NO, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIssueCommand.MESSAGE_USAGE));
        }

        RoomNumber roomNumber = ParserUtil.parseRoomNumber(argMultimap.getValue(PREFIX_ROOM_NO).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Timestamp timestamp = ParserUtil
                .parseTimestamp(argMultimap.getValue(PREFIX_TIMESTAMP).orElse(new Timestamp().toString()));
        Status status = ParserUtil
                .parseStatus(argMultimap.getValue(PREFIX_STATUS).orElse(IssueStatus.Pending.toString()));
        Category category = ParserUtil
                .parseCategory(argMultimap.getValue(PREFIX_CATEGORY).orElse(Category.NO_CATEGORY_NAME));

        Issue issue = new Issue(roomNumber, description, timestamp, status, category);

        return new AddIssueCommand(issue);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
