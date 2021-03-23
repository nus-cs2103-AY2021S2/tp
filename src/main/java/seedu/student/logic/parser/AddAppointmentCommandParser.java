package seedu.student.logic.parser;

import static seedu.student.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.student.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.student.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.student.logic.parser.CliSyntax.PREFIX_MATRICULATION_NUMBER;
import static seedu.student.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Stream;

import seedu.student.logic.commands.AddAppointmentCommand;
import seedu.student.logic.parser.exceptions.ParseException;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.student.MatriculationNumber;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        assert args.length() == 4;
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MATRICULATION_NUMBER, PREFIX_DATE, PREFIX_START_TIME,
                        PREFIX_END_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_MATRICULATION_NUMBER, PREFIX_DATE, PREFIX_START_TIME,
                PREFIX_END_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE));
        }

        MatriculationNumber matriculationNumber = ParserUtil.parseMatric(argMultimap
                .getValue(PREFIX_MATRICULATION_NUMBER).get());
        LocalDate date = LocalDate.parse(argMultimap.getValue(PREFIX_DATE).get());
        LocalTime startTime = LocalTime.parse(argMultimap.getValue(PREFIX_START_TIME).get());
        LocalTime endTime = LocalTime.parse(argMultimap.getValue(PREFIX_END_TIME).get());

        Appointment appointment = new Appointment(matriculationNumber, date, startTime, endTime);

        return new AddAppointmentCommand(appointment);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
