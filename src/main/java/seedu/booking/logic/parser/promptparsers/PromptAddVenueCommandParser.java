package seedu.booking.logic.parser.promptparsers;

import static seedu.booking.commons.core.Messages.DEFAULT_VENUE_DESCRIPTION;
import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.booking.logic.commands.PromptAddVenueCommand;
import seedu.booking.logic.commands.PromptVenueCapacityCommand;
import seedu.booking.logic.commands.PromptVenueDescCommand;
import seedu.booking.logic.commands.PromptVenueTagsCommand;
import seedu.booking.logic.parser.ArgumentMultimap;
import seedu.booking.logic.parser.ArgumentTokenizer;
import seedu.booking.logic.parser.ParserUtil;
import seedu.booking.logic.parser.Prefix;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.Tag;
import seedu.booking.model.venue.Capacity;
import seedu.booking.model.venue.VenueName;

/**
 * Parses input arguments and creates a new PromptAddVenueCommand object
 */
public class PromptAddVenueCommandParser {
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
     * Parses the given {@code String} of arguments for capacity in the context of adding a venue.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PromptVenueCapacityCommand parseCapacity(String args) throws ParseException {
        Capacity capacity;
        if (args.equals("")) {
            capacity = new Capacity(10);
        } else {
            capacity = ParserUtil.parseCapacity(args);
        }

        return new PromptVenueCapacityCommand(capacity);
    }

    /**
     * Parses the given {@code String} of arguments for description in the context of adding a venue.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PromptVenueDescCommand parseDescription(String args) throws ParseException {
        String description;
        if (args.equals("")) {
            description = DEFAULT_VENUE_DESCRIPTION;
        } else {
            description = ParserUtil.parseDescription(args);
        }

        return new PromptVenueDescCommand(description);
    }

    /**
     * Parses the given {@code String} of arguments for tags in the context of adding a venue.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PromptVenueTagsCommand parseTags(String args) throws ParseException {
        Set<Tag> tags = ParserUtil.parseTagsForPromptCommands(args);

        return new PromptVenueTagsCommand(tags);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
