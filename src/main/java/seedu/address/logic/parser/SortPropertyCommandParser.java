package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTING_KEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTING_ORDER;

import seedu.address.logic.commands.sort.SortPropertyCommand;
import seedu.address.logic.commands.sort.SortPropertyCommand.SortPropertyDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortPropertyCommand object.
 */
public class SortPropertyCommandParser implements Parser<SortPropertyCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SortPropertyCommand
     * and returns an SortPropertyCommand object for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public SortPropertyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SORTING_ORDER, PREFIX_SORTING_KEY);

        SortPropertyDescriptor sortPropertyDescriptor =
                new SortPropertyDescriptor();
        if (argMultimap.getValue(PREFIX_SORTING_ORDER).isPresent()) {
            sortPropertyDescriptor.setSortingOrder(ParserUtil.parseSortingOrder(
                    argMultimap.getValue(PREFIX_SORTING_ORDER).get()));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortPropertyCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_SORTING_KEY).isPresent()) {
            sortPropertyDescriptor.setPropertySortingKey(ParserUtil.parsePropertySortingKey(
                    argMultimap.getValue(PREFIX_SORTING_KEY).get()));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortPropertyCommand.MESSAGE_USAGE));
        }
        return new SortPropertyCommand(sortPropertyDescriptor);
    }

}
