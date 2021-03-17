package seedu.booking.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.stream.Stream;

import seedu.booking.logic.commands.DeleteVenueCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.venue.Venue;

/**
 * Parses input arguments and creates a new DeleteVenueCommand object.
 */
public class DeleteVenueCommandParser implements Parser<DeleteVenueCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteVenueCommand
     * and returns a DeleteVenueCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteVenueCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_VENUE);

        Venue venue;
        if (!arePrefixesPresent(argMultimap, PREFIX_VENUE)
                || argMultimap.getValue(PREFIX_VENUE).isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteVenueCommand.MESSAGE_USAGE));
        }
        venue = ParserUtil.parseVenue(argMultimap.getValue(PREFIX_VENUE).get());
        return new DeleteVenueCommand(venue);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
