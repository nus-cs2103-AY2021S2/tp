package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.model.session.RecurringSession.isValidEnd;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddRecurringSessionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.session.Duration;
import seedu.address.model.session.Fee;
import seedu.address.model.session.Interval;
import seedu.address.model.session.RecurringSession;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionDate;
import seedu.address.model.session.Subject;
import seedu.address.model.student.Name;



public class AddRecurringSessionCommandParser implements Parser<AddRecurringSessionCommand> {
    private static final String MESSAGE_LAST_BEFORE_START = "Last date specified is before the starting date.";
    private static final String MESSAGE_UNUSED_RECURRENCE = "The session is not recurring, "
            + "add as a single session with add_session "
            + "or edit the last date.";
    @Override
    public AddRecurringSessionCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_DATE, PREFIX_TIME, PREFIX_DURATION,
                        PREFIX_SUBJECT, PREFIX_FEE, PREFIX_INTERVAL, PREFIX_END_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DATE, PREFIX_TIME, PREFIX_DURATION,
                PREFIX_SUBJECT, PREFIX_FEE, PREFIX_INTERVAL, PREFIX_END_DATE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecurringSessionCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        SessionDate sessionDate = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATE).get(),
                argMultimap.getValue(PREFIX_TIME).get());
        Duration duration = ParserUtil.parseDuration(argMultimap.getValue(PREFIX_DURATION).get());
        Subject subject = ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get());
        Fee fee = ParserUtil.parseFee(argMultimap.getValue(PREFIX_FEE).get());
        Interval interval = ParserUtil.parseInterval(argMultimap.getValue(PREFIX_INTERVAL).get());
        SessionDate lastDateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_END_DATE).get(),
                argMultimap.getValue(PREFIX_TIME).get());

        if (!Session.isPossibleEndTime(sessionDate, duration)) {
            throw new ParseException(Session.MESSAGE_CONSTRAINTS);
        }

        if (!isValidEnd(sessionDate, lastDateTime, interval)) {
            if (lastDateTime.getDate().isBefore(sessionDate.getDate())) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        MESSAGE_LAST_BEFORE_START));
            }
            LocalDate possibleDate = RecurringSession.lastValidDateOnOrBefore(lastDateTime, sessionDate, interval);
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecurringSession.MESSAGE_CONSTRAINTS + "\n"
                            + "Did you mean " + possibleDate + " for last date?"));
        }

        if (sessionDate.equals(lastDateTime)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_UNUSED_RECURRENCE));
        }

        RecurringSession recurringSession =
                new RecurringSession(sessionDate, duration, subject, fee, interval, lastDateTime);
        return new AddRecurringSessionCommand(recurringSession, name);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
