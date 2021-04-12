package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnscheduleCommand;
import seedu.address.logic.parser.exceptions.InvalidIntegerException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class UnscheduleCommandParser implements Parser<UnscheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ScheduleCommand
     * and returns a ScheduleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnscheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        if (args.trim().equalsIgnoreCase("all")) {
            return new UnscheduleCommand(null, true, false);
        }
        if (args.trim().equalsIgnoreCase("expired")) {
            return new UnscheduleCommand(null, false, true);
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(args);
        } catch (InvalidIntegerException pe) {
            throw new ParseException(MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnscheduleCommand.MESSAGE_USAGE));
        }

        return new UnscheduleCommand(index, false, false);
    }

}
