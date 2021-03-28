package seedu.address.logic.commands.eventcommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Opens timetable for display.
 */
public class ViewTimeTableCommand extends Command {

    public static final String COMMAND_WORD = "timetable";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows tutee's timetable events.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_OPEN_MESSAGE = "Opened timetable window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_OPEN_MESSAGE, false, false, true);
    }
}
