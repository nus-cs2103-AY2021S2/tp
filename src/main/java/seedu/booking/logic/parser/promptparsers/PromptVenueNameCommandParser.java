package seedu.booking.logic.parser.promptparsers;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.stream.Stream;

import seedu.booking.logic.commands.PromptAddVenueCommand;
import seedu.booking.logic.parser.ArgumentMultimap;
import seedu.booking.logic.parser.ArgumentTokenizer;
import seedu.booking.logic.parser.Parser;
import seedu.booking.logic.parser.ParserUtil;
import seedu.booking.logic.parser.Prefix;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.venue.VenueName;

/**
 * Parses input arguments and creates a new PromptAddVenueCommand object
 */
public class PromptVenueNameCommandParser implements Parser<PromptAddVenueCommand> {

    /**
     * Parses the given {@code String} of arguments for venue in the context of adding a venue
     * and returns an PromptAddVenue object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PromptAddVenueCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_VENUE);

        if (!arePrefixesPresent(argMultimap, PREFIX_VENUE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    PromptAddVenueCommand.MESSAGE_USAGE));
        }

        VenueName venueName = ParserUtil.parseVenueName(argMultimap.getValue(PREFIX_VENUE).get());

        return new PromptAddVenueCommand(venueName);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
