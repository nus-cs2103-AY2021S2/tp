package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.ParserUtil.parseAppointmentDate;
import static seedu.address.logic.parser.ParserUtil.parseAppointmentTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDatePredicate;
import seedu.address.model.appointment.AppointmentNamePredicate;
import seedu.address.model.appointment.AppointmentPredicateList;
import seedu.address.model.appointment.AppointmentRemarksPredicate;
import seedu.address.model.appointment.AppointmentTimePredicate;

/**
 * Parses input arguments and creates a new FindAppointmentCommand object
 */
public class FindAppointmentCommandParser implements Parser<FindAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindAppointmentCommand
     * and returns a FindAppointmentCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_REMARK, PREFIX_DATE, PREFIX_TIME);

        if (args.strip().equals("") || !checkMultiMap(argMultimap)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE)
            );
        }

        List<Predicate<Appointment>> predicates = new ArrayList<>();
        List<AppointmentPredicateList> orPredicates = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            List<Predicate<Appointment>> nameList = new ArrayList<>();
            try {
                argMultimap.getAllValues(PREFIX_NAME)
                    .forEach(s -> {
                        nameList.add(new AppointmentNamePredicate(Arrays.asList(s.split("\\s+"))));
                    });
            } catch (IllegalArgumentException e) {
                throw new ParseException("n/ used but no keywords found! \n"
                        + e.getMessage()
                        + "\n"
                        + FindAppointmentCommand.MESSAGE_USAGE);
            }
            orPredicates.add(new AppointmentPredicateList(nameList));
        }

        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            List<String> remarks = argMultimap.getAllValues(PREFIX_REMARK);
            for (String s : remarks) {
                try {
                    predicates.add(new AppointmentRemarksPredicate(s));
                } catch (IllegalArgumentException e) {
                    throw new ParseException("r/ used but no remarks found! \n"
                            + e.getMessage()
                            + "\n"
                            + FindAppointmentCommand.MESSAGE_USAGE);
                }
            }
        }

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            List<String> date = argMultimap.getAllValues(PREFIX_DATE);
            List<Predicate<Appointment>> dateList = new ArrayList<>();
            try {
                for (String s : date) {
                    dateList.add(new AppointmentDatePredicate(parseAppointmentDate(s)));
                }
            } catch (ParseException e) {
                throw new ParseException("Wrong date format! \n"
                        + e.getMessage()
                        + "\n"
                        + FindAppointmentCommand.MESSAGE_USAGE);
            }
            orPredicates.add(new AppointmentPredicateList(dateList));
        }

        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            List<String> time = argMultimap.getAllValues(PREFIX_TIME);
            List<Predicate<Appointment>> timeList = new ArrayList<>();
            try {
                for (String s : time) {
                    timeList.add(new AppointmentTimePredicate(parseAppointmentTime(s)));
                }
            } catch (ParseException e) {
                throw new ParseException("Wrong time format! \n"
                        + e.getMessage()
                        + "\n"
                        + FindAppointmentCommand.MESSAGE_USAGE);
            }
            orPredicates.add(new AppointmentPredicateList(timeList));
        }
        return new FindAppointmentCommand(new AppointmentPredicateList(predicates, orPredicates));
    }

    private boolean checkMultiMap(ArgumentMultimap args) {
        List<Prefix> prefixes = Arrays.asList(PREFIX_NAME, PREFIX_REMARK, PREFIX_DATE, PREFIX_TIME);
        for (Prefix p : prefixes) {
            if (args.getValue(p).isPresent()) {
                return true;
            }
        }
        return false;
    }
}
