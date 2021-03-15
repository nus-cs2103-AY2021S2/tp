package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;

import seedu.address.logic.commands.DeleteVenueCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.booking.Venue;

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
        try {
            venue = ParserUtil.parseVenue(argMultimap.getValue(PREFIX_VENUE).get());
            return new DeleteVenueCommand(venue);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteVenueCommand.MESSAGE_USAGE), pe);
        }
    }

}
