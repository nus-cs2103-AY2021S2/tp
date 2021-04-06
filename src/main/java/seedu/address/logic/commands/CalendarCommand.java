package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class CalendarCommand extends Command {

    public static final String COMMAND_WORD = "calendar";
    public static final String COMMAND_CHAR = "c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_CALENDAR = "Opened Calendar window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_CALENDAR, false,
                true, false);
    }
}
