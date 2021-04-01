package seedu.address.logic.parser.scheduleparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.schedulecommands.ViewScheduleCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.schedule.ScheduleDateViewPredicate;

/**
 * Parses input arguments and creates a new {@code ViewScheduleCommand} object
 */
public class ViewScheduleCommandParser implements Parser<ViewScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code ViewScheduleCommand}
     * and returns a {@code ViewAppointmentCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewScheduleCommand parse(String args) throws ParseException {
        try {
            AppointmentDateTime appointmentDateTime = ParserUtil.parseDateTime(args + " 12:00AM");
            return new ViewScheduleCommand(new ScheduleDateViewPredicate(appointmentDateTime));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewScheduleCommand.MESSAGE_USAGE), pe);
        }
    }

}
