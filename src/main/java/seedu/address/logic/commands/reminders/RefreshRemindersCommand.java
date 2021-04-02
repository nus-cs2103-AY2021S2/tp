package seedu.address.logic.commands.reminders;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

public class RefreshRemindersCommand extends Command {

    public static final String COMMAND_WORD = "refresh";

    public static final String MESSAGE_SUCCESS = "Refreshed reminders";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.refreshReminderBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
