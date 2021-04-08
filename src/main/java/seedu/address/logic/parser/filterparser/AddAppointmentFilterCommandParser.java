package seedu.address.logic.parser.filterparser;

import static seedu.address.commons.core.Messages.MESSAGE_NO_FILTER_PROVIDED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_TO;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.filtercommands.AddAppointmentFilterCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.filter.AddressFilter;
import seedu.address.model.filter.AppointmentDateTimeFilter;
import seedu.address.model.filter.AppointmentFilter;
import seedu.address.model.filter.NameFilter;
import seedu.address.model.filter.SubjectNameFilter;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.tutor.Address;
import seedu.address.model.tutor.Name;

/**
 * Parses input arguments and creates a new AddAppointmentFilterCommand object
 */
public class AddAppointmentFilterCommandParser implements Parser<AddAppointmentFilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAppointmentFilterCommand
     * and returns an AddAppointmentFilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAppointmentFilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SUBJECT_NAME, PREFIX_TIME_FROM,
                        PREFIX_TIME_TO, PREFIX_LOCATION);

        Set<Predicate<Name>> nameFilters = new LinkedHashSet<>(
                argMultimap.getAllValues(PREFIX_NAME).stream()
                        .map(NameFilter::new)
                        .collect(Collectors.toList()));

        Set<Predicate<SubjectName>> subjectNameFilters = new LinkedHashSet<>(
                argMultimap.getAllValues(PREFIX_SUBJECT_NAME).stream()
                        .map(SubjectNameFilter::new)
                        .collect(Collectors.toList()));

        Set<Predicate<AppointmentDateTime>> timeFromFilters = new LinkedHashSet<>(
                argMultimap.getAllValues(PREFIX_TIME_FROM).stream()
                        .map(AppointmentDateTimeFilter::new)
                        .collect(Collectors.toList()));

        Set<Predicate<AppointmentDateTime>> timeToFilters = new LinkedHashSet<>(
                argMultimap.getAllValues(PREFIX_TIME_TO).stream()
                        .map(AppointmentDateTimeFilter::new)
                        .collect(Collectors.toList()));

        Set<Predicate<Address>> locationFilters = new LinkedHashSet<>(
                argMultimap.getAllValues(PREFIX_LOCATION).stream()
                        .map(AddressFilter::new)
                        .collect(Collectors.toList()));

        if (!CollectionUtil.isAnyNotEmpty(
                nameFilters,
                subjectNameFilters,
                timeFromFilters,
                timeToFilters,
                locationFilters)) {
            throw new ParseException(MESSAGE_NO_FILTER_PROVIDED);
        }

        // TODO: Throw ParseException
        // TODO: Maybe switch to using ParserUtil

        AppointmentFilter appointmentFilter = new AppointmentFilter(nameFilters,
                subjectNameFilters,
                timeFromFilters,
                timeToFilters,
                locationFilters);

        return new AddAppointmentFilterCommand(appointmentFilter);
    }
}
