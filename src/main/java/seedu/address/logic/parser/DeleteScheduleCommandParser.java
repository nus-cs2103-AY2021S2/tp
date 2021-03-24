package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.ScheduleDescription;

/**
 * Parses input arguments and creates a new DeleteScheduleCommand object
 */
public class DeleteScheduleCommandParser implements Parser<DeleteScheduleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of DeleteScheduleCommand
     * and returns a DeleteScheduleCommand object for execution
     * @throws ParseException if the user does not conform to the expected format
     */
    public DeleteScheduleCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, DeleteScheduleCommand.MESSAGE_USAGE));
        }

        ScheduleDescription scheduleDescription = new ScheduleDescription(trimmedArgs);

        return new DeleteScheduleCommand(scheduleDescription);
    }
}
