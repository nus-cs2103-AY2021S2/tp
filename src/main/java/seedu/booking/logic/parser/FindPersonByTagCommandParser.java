package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.booking.logic.commands.FindPersonByTagCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.booking.PersonContainsTagPredicate;

/**
 * Parses input arguments and creates a new FindPersonByTagCommand object.
 */
public class FindPersonByTagCommandParser implements Parser<FindPersonByTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterBookingByVenueCommandParser
     * and returns a FilterBookingByVenueCommandParser object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FindPersonByTagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);
        if (!arePrefixesPresent(argMultimap, PREFIX_TAG)
                || argMultimap.getValue(PREFIX_TAG).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindPersonByTagCommand.MESSAGE_USAGE));
        }

        String tagName = argMultimap.getValue(PREFIX_TAG).get();
        assert !tagName.isEmpty() : "tagName should not be empty";

        return new FindPersonByTagCommand(new PersonContainsTagPredicate(tagName));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
