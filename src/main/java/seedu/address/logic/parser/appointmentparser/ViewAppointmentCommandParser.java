package seedu.address.logic.parser.appointmentparser;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.appointmentcommands.ViewAppointmentCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.appointment.DateViewPredicate;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new {@code ViewAppointmentCommand} object
 */
public class ViewAppointmentCommandParser implements Parser<ViewAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code ViewAppointmentCommand}
     * and returns a {@code ViewAppointmentCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewAppointmentCommand parse(String args) throws ParseException {
        try {
            AppointmentDateTime appointmentDateTime = ParserUtil.parseDateTime(args);
            return new ViewAppointmentCommand(new DateViewPredicate(appointmentDateTime));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
