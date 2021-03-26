package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTING_KEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTING_ORDER;

import seedu.address.logic.commands.sort.SortAppointmentCommand;
import seedu.address.logic.commands.sort.SortAppointmentCommand.SortAppointmentDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortAppointmentCommand object.
 */
public class SortAppointmentCommandParser implements Parser<SortAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortAppointmentCommand
     * and returns an SortAppointmentCommand object for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public SortAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SORTING_ORDER, PREFIX_SORTING_KEY);

        SortAppointmentDescriptor sortAppointmentDescriptor =
                new SortAppointmentDescriptor();
        if (argMultimap.getValue(PREFIX_SORTING_ORDER).isPresent()) {
            sortAppointmentDescriptor.setSortingOrder(ParserUtil.parseSortingOrder(
                    argMultimap.getValue(PREFIX_SORTING_ORDER).get()));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortAppointmentCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_SORTING_KEY).isPresent()) {
            sortAppointmentDescriptor.setAppointmentSortingKey(ParserUtil.parseAppointmentSortingKey(
                    argMultimap.getValue(PREFIX_SORTING_KEY).get()));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortAppointmentCommand.MESSAGE_USAGE));
        }
        return new SortAppointmentCommand(sortAppointmentDescriptor);
    }

}
