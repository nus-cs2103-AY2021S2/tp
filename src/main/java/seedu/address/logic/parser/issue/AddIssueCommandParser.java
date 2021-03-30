package seedu.address.logic.parser.issue;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESTAMP;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.issue.AddIssueCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.issue.Category;
import seedu.address.model.issue.Description;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueStatus;
import seedu.address.model.issue.RoomNumber;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Timestamp;
import seedu.address.model.tag.Tag;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ROOM_NUMBER, PREFIX_DESCRIPTION,
                PREFIX_TIMESTAMP, PREFIX_STATUS, PREFIX_CATEGORY, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROOM_NUMBER, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIssueCommand.MESSAGE_USAGE));
        }

        RoomNumber roomNumber = ParserUtil.parseIssueRoomNumber(argMultimap.getValue(PREFIX_ROOM_NUMBER).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Timestamp timestamp = ParserUtil
                .parseTimestamp(argMultimap.getValue(PREFIX_TIMESTAMP).orElse(new Timestamp().toString()));
        Status status = ParserUtil
                .parseStatus(argMultimap.getValue(PREFIX_STATUS).orElse(IssueStatus.Pending.toString()));
        Category category = ParserUtil
                .parseCategory(argMultimap.getValue(PREFIX_CATEGORY).orElse(Category.NO_CATEGORY_NAME));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Issue issue = new Issue(roomNumber, description, timestamp, status, category, tagList);

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
