package seedu.address.logic.parser.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT_START;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.appointment.FindAppointmentCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.TimeslotParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindAppointmentCommand object
 */
public class FindAppointmentCommandParser implements Parser<FindAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindAppointmentCommand
     * and returns a FindAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindAppointmentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PATIENT, PREFIX_DOCTOR,
                PREFIX_TIMESLOT_START, PREFIX_TAG);

        if (!areAnyPrefixesPresent(argMultimap, PREFIX_PATIENT, PREFIX_DOCTOR,
                PREFIX_TIMESLOT_START, PREFIX_TAG)) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
        }

        List<String> patientKeywords = new ArrayList<String>();
        List<String> doctorKeywords = new ArrayList<String>();
        List<String> timeStartKeywords = new ArrayList<String>();
        List<String> tagKeywords = new ArrayList<String>();

        if (argMultimap.getValue(PREFIX_PATIENT).isPresent()) {
            Collections.addAll(patientKeywords, listKeywords(argMultimap, PREFIX_PATIENT));
        }

        if (argMultimap.getValue(PREFIX_DOCTOR).isPresent()) {
            Collections.addAll(doctorKeywords, listKeywords(argMultimap, PREFIX_DOCTOR));
        }

        if (argMultimap.getValue(PREFIX_TIMESLOT_START).isPresent()) {
            String[] standardDateTimeInput = listKeywords(argMultimap, PREFIX_TIMESLOT_START);
            //align with dateTimeFormat in timeslot
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            for (int i = 0; i <= standardDateTimeInput.length - 1; i++) {
                String s = standardDateTimeInput[i].toUpperCase().trim();
                if (s.contains("/") || s.contains("-")) {
                    standardDateTimeInput[i] = format.format(TimeslotParser.parseStandardDate(s));
                }
                if (s.contains("AM") || s.contains("PM")) {
                    standardDateTimeInput[i] = TimeslotParser.parseStandardTime(s);
                }

            }
            Collections.addAll(timeStartKeywords, standardDateTimeInput);
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            Collections.addAll(tagKeywords, listKeywords(argMultimap, PREFIX_TAG));
        }
        return new FindAppointmentCommand(new AppointmentContainsKeywordsPredicate(patientKeywords,
                doctorKeywords, timeStartKeywords, tagKeywords));
    }

    /**
     * Returns true if any one of the prefixes is non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Converts the keyword input by user into a String array
     *
     * @param argMultimap
     * @param prefix
     * @return String Array of all Keywords
     * @throws ParseException
     */
    public String[] listKeywords(ArgumentMultimap argMultimap, Prefix prefix) throws ParseException {
        String keywords = argMultimap.getValue(prefix).get();

        requireNonNull(keywords);
        String trimmedKeywords = keywords.trim();
        if (trimmedKeywords.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
        }
        return trimmedKeywords.split("\\s+");
    }

}
