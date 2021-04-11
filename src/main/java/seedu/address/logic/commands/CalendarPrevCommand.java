package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.CalendarDirection;
import seedu.address.model.Model;

/**
 * This command navigates the calendar to the previous month.
 */
public class CalendarPrevCommand extends Command {

    public static final String COMMAND_WORD = "prev";
    public static final String MESSAGE_SUCCESS = "Calendar is showing the previous month.";
    public static final String SHORT_MESSAGE_USAGE = COMMAND_WORD + "\n";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, true, CalendarDirection.PREV, false);
    }
}
