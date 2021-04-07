package seedu.address.logic.commands.remindercommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.reminder.Reminder;

/**
 * Deletes a reminder identified using it's displayed index from the reminder list.
 */
public class DeleteReminderCommand extends Command {

    public static final String COMMAND_WORD = "delete_reminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the reminder identified by the index number used in the displayed reminder list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_REMINDER_SUCCESS = "Deleted Reminder: %1$s";

    private final Index targetIndex;

    /**
     * Create {@code DeleteReminderCommand} with target index to delete.
     *
     * @param targetIndex Target index of reminder to delete.
     */
    public DeleteReminderCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    /**
     * Deletes reminder if exists in reminder list.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Command Result indicating success or failure of delete operation
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Reminder> lastShownList = model.getFilteredReminderList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX);
        }

        Reminder reminderToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteReminder(reminderToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_REMINDER_SUCCESS, reminderToDelete), TabName.REMINDER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteReminderCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteReminderCommand) other).targetIndex)); // state check
    }
}
