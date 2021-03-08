package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TIME_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Meeting;

public class AddMeetingParser implements Parser<AddMeetingCommand> {

    private static final DateTimeFormatter DATE_PARSER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter TIME_PARSER = DateTimeFormatter.ofPattern("HHmm");

    public AddMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_TIME, PREFIX_DESCRIPTION);

        Index index;
        LocalDate date;
        LocalTime time;
        String description;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_TIME, PREFIX_DESCRIPTION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
        }

        try {
            date = DATE_PARSER.parse(argMultimap.getValue(PREFIX_DATE).get(), LocalDate::from);
        } catch (DateTimeParseException dte) {
            throw new ParseException(String.format(MESSAGE_INVALID_DATE_FORMAT,
                    "Only accept dd-MM-yyyy, e.g. 12-12-2020"));
        }

        try {
            time = TIME_PARSER.parse(argMultimap.getValue(PREFIX_TIME).get(), LocalTime::from);
        } catch (DateTimeParseException dte) {
            throw new ParseException(String.format(MESSAGE_INVALID_TIME_FORMAT, "Only accepts HHmm, e.g. 1200"));
        }

        description = argMultimap.getValue(PREFIX_DESCRIPTION).get();

        return new AddMeetingCommand(index, new Meeting(date, time, description));
    }
}
