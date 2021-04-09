package seedu.address.logic.commands.eventcommands;

import java.time.LocalDate;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Opens timetable for display.
 */
public class ViewTimeTableCommand extends Command {

    public static final String COMMAND_WORD = "timetable";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows tutee's timetable events.\n"
            + "Example: " + COMMAND_WORD + " [DATE]";

    public static final String SHOWING_OPEN_MESSAGE = "Opened timetable window.";

    private final LocalDate queryDate;

    public ViewTimeTableCommand(LocalDate queryDate) {
        this.queryDate = queryDate;
    }

    @Override
    public CommandResult execute(Model model) {
        model.setTimeTableDate(queryDate);
        return new CommandResult(SHOWING_OPEN_MESSAGE, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewTimeTableCommand // instanceof handles nulls
                && queryDate.equals(((ViewTimeTableCommand) other).queryDate)); // state check
    }
}
