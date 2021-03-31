package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.common.Date;
import seedu.address.model.event.EventFindSchedulePredicate;
import seedu.address.model.task.TaskFindSchedulePredicate;

/**
 * Parses input arguments and creates a new FindScheduleCommand object.
 */
public class FindScheduleCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the FindScheduleCommand
     * and returns a FindScheduleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindScheduleCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindScheduleCommand.MESSAGE_USAGE));
        }

        Date byDate = SocheduleParserUtil.parseDate(trimmedArgs);

        return new FindScheduleCommand(new TaskFindSchedulePredicate(byDate),
                new EventFindSchedulePredicate(byDate));
    }
}
