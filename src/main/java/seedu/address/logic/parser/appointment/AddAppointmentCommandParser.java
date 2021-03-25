package seedu.address.logic.parser.appointment;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT_START;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.appointment.AddAppointmentCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddAppointmentCommand object
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddAppointmentCommand
     * and returns an AddAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PATIENT, PREFIX_DOCTOR,
                PREFIX_TIMESLOT_START, PREFIX_TIMESLOT_END, PREFIX_TIMESLOT_DURATION, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_PATIENT, PREFIX_DOCTOR, PREFIX_TIMESLOT_START)
                || !areAnyPrefixesPresent(argMultimap, PREFIX_TIMESLOT_END, PREFIX_TIMESLOT_DURATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE));
        }

        Index patientIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PATIENT).get());
        String doctor = argMultimap.getValue(PREFIX_DOCTOR).get();
        Timeslot timeslot = (argMultimap.getValue(PREFIX_TIMESLOT_END).isPresent())
                ? ParserUtil.parseTimeslotByEnd(argMultimap.getValue(PREFIX_TIMESLOT_START).get(),
                        argMultimap.getValue(PREFIX_TIMESLOT_END).get())
                : ParserUtil.parseTimeslotByDuration(argMultimap.getValue(PREFIX_TIMESLOT_START).get(),
                        argMultimap.getValue(PREFIX_TIMESLOT_DURATION).get());

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        return new AddAppointmentCommand(patientIndex, doctor, timeslot, tagList);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if any one of the prefixes is non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
