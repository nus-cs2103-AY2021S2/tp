package seedu.booking.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_CAPACITY;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.booking.logic.commands.FindVenueCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.venue.Venue;

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
        requireNonNull(args);

        List<Predicate<Venue>> predicates = new ArrayList<>();

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_VENUE, PREFIX_CAPACITY,
                PREFIX_DESCRIPTION, PREFIX_TAG);

        if (argMultimap.getValue(PREFIX_VENUE).isPresent()) {
            predicates.add(ParserUtil.parseVenueNameContainsKeywordsPredicate(argMultimap
                    .getValue(PREFIX_VENUE).get()));
        }

        if (argMultimap.getValue(PREFIX_CAPACITY).isPresent()) {
            predicates.add(ParserUtil.parseCapacityContainsKeywordsPredicate(argMultimap
                    .getValue(PREFIX_CAPACITY).get()));
        }

        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            predicates.add(ParserUtil.parseVenueDescContainsKeywordsPredicate(argMultimap
                    .getValue(PREFIX_DESCRIPTION).get()));
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            predicates.add(ParserUtil.parseVenueTagContainsKeywordsPredicate(argMultimap.getValue(PREFIX_TAG).get()));
        }

        if (predicates.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindVenueCommand.MESSAGE_USAGE));
        }
        return new FindVenueCommand(predicates);

    }

}
