package seedu.address.logic.parser.eventparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.eventcommands.ViewEventCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentDateTime;

/**
 * Parses input arguments and creates a new {@code ViewEventCommand} object
 */
public class ViewEventCommandParser implements Parser<ViewEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code ViewEventCommand}
     * and returns a {@code ViewEventCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewEventCommand parse(String args) throws ParseException {
        try {
            AppointmentDateTime appointmentDateTime = ParserUtil.parseDateTime(args + " 12:00AM");
            return new ViewEventCommand(appointmentDateTime);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewEventCommand.MESSAGE_USAGE), pe);
        }
    }

}
