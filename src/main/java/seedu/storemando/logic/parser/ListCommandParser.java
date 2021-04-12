package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.storemando.logic.commands.ListCommand;
import seedu.storemando.logic.parser.exceptions.ParseException;
import seedu.storemando.model.item.LocationContainsKeywordsPredicate;
import seedu.storemando.model.tag.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ListCommand object.
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     *
     * @param args Parses the given {@code String} of arguments in the context of the ListCommand.
     * @return a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public ListCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new ListCommand();
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LOCATION, PREFIX_TAG);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_LOCATION).isPresent() && argMultimap.getValue(PREFIX_TAG).isEmpty()) {
            String locationKeywords = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get()).toString();
            String[] keywords = locationKeywords.split("\\s+");
            return new ListCommand(new LocationContainsKeywordsPredicate(Arrays.asList(keywords)),
                Arrays.asList(keywords));
        } else if (argMultimap.getValue(PREFIX_TAG).isPresent() && argMultimap.getValue(PREFIX_LOCATION).isEmpty()) {
            String tagKeywords = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get()).tagName;
            String[] keywords = tagKeywords.split("\\s+");
            return new ListCommand(new TagContainsKeywordsPredicate(Arrays.asList(keywords)), Arrays.asList(keywords));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
    }
}
