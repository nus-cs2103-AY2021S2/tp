package seedu.address.logic.parser.filterparser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_TO;

import seedu.address.logic.commands.filtercommands.DeleteAppointmentFilterCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.filter.AppointmentFilter;

/**
 * Parses input arguments and creates a new DeleteAppointmentFilterCommand object
 */
public class DeleteAppointmentFilterCommandParser implements Parser<DeleteAppointmentFilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteAppointmentFilterCommand
     * and returns an DeleteAppointmentFilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteAppointmentFilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SUBJECT_NAME, PREFIX_TIME_FROM,
                        PREFIX_TIME_TO, PREFIX_LOCATION);

        AppointmentFilter appointmentFilter = FilterParserUtil.parseAppointmentFilter(
                argMultimap.getAllValues(PREFIX_NAME),
                argMultimap.getAllValues(PREFIX_SUBJECT_NAME),
                argMultimap.getAllValues(PREFIX_TIME_FROM),
                argMultimap.getAllValues(PREFIX_TIME_TO),
                argMultimap.getAllValues(PREFIX_LOCATION));

        return new DeleteAppointmentFilterCommand(appointmentFilter);
    }
}
