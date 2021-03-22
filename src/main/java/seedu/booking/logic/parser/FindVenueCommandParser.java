package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.booking.logic.commands.FindVenueCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.booking.VenueNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindVenueCommand object.
 */
public class FindVenueCommandParser implements Parser<FindVenueCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindVenueCommand
     * and returns a FindVenueCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindVenueCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_VENUE);
        if (!arePrefixesPresent(argMultimap, PREFIX_VENUE)
                || argMultimap.getValue(PREFIX_VENUE).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindVenueCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = argMultimap.getValue(PREFIX_VENUE).get().split(" ");

        return new FindVenueCommand(new VenueNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
