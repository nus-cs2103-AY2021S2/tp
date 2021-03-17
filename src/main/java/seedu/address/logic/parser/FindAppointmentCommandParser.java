package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT_START;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.commands.FindPatientCommand;
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
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DOCTOR,
                PREFIX_TIMESLOT_START, PREFIX_TAG);

        if (!areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DOCTOR,
                PREFIX_TIMESLOT_START, PREFIX_TAG)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
        }

        ArrayList<String> patientKeywords = new ArrayList<String>();
        ArrayList<String> doctorKeywords = new ArrayList<String>();
        ArrayList<String> timeStartKeywords = new ArrayList<String>();
        ArrayList<String> tagKeywords = new ArrayList<String>();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] xs = listKeywords(argMultimap, PREFIX_NAME);
            Collections.addAll(patientKeywords, xs);
        }

        if (argMultimap.getValue(PREFIX_DOCTOR).isPresent()) {
            String[] xs = listKeywords(argMultimap, PREFIX_DOCTOR);
            Collections.addAll(doctorKeywords, xs);
        }

        if (argMultimap.getValue(PREFIX_TIMESLOT_START).isPresent()) {
            String[] xs = listKeywords(argMultimap, PREFIX_TIMESLOT_START);
            Collections.addAll(timeStartKeywords, xs);
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            String[] xs = listKeywords(argMultimap, PREFIX_TAG);
            Collections.addAll(tagKeywords, xs);
        }
        return new FindAppointmentCommand(new AppointmentContainsKeywordsPredicate(patientKeywords ,
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
        String keywords = "";
        keywords = argMultimap.getValue(prefix).get();

        requireNonNull(keywords);
        String trimmedKeywords = keywords.trim();
        if (trimmedKeywords.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
        }
        return trimmedKeywords.split("\\s+");
    }

}
