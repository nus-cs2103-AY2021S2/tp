package seedu.address.logic.parser.scheduleparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_TO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.schedulecommands.AddScheduleCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.schedule.Description;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.Title;

/**
 * Parses input arguments and creates a new AddScheduleCommand object
 */
public class AddScheduleCommandParser implements Parser<AddScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddScheduleCommand
     * and returns an AddScheduleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddScheduleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DATE, PREFIX_TIME_FROM,
                        PREFIX_TIME_TO, PREFIX_DESCRIPTION);

        if (!ArgumentTokenizer.arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_DATE, PREFIX_TIME_FROM,
                PREFIX_TIME_TO, PREFIX_DESCRIPTION) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, AddScheduleCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        String dateString = argMultimap.getValue(PREFIX_DATE).get();
        String timeFromString = argMultimap.getValue(PREFIX_TIME_FROM).get();
        String timeToString = argMultimap.getValue(PREFIX_TIME_TO).get();
        AppointmentDateTime timeFrom = ParserUtil.parseDateTime(dateString + " " + timeFromString);
        AppointmentDateTime timeTo = ParserUtil.parseDateTime(dateString + " " + timeToString);
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());

        Schedule schedule = new Schedule(title, timeFrom, timeTo, description);

        return new AddScheduleCommand(schedule);
    }
}
