package seedu.address.logic.commands.remindercommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_REMINDER;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all reminders in the reminder list to the user.
 */
public class ListReminderCommand extends Command {

    public static final String COMMAND_WORD = "list_reminders";

    public static final String MESSAGE_SUCCESS = "Listed all reminders";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredReminderList(PREDICATE_SHOW_ALL_REMINDER);
        return new CommandResult(MESSAGE_SUCCESS, TabName.REMINDER);
    }
}
