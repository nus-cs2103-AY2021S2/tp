package seedu.address.logic.commands.remindercommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.reminder.Reminder;

/**
 * Adds a reminder to the reminder list.
 */
public class AddReminderCommand extends Command {

    public static final String COMMAND_WORD = "add_reminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a reminder to the reminder list. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_DATE + "REMINDER DATE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Maths Tuition Payment Due "
            + PREFIX_DATE + "2021-3-31 ";

    public static final String MESSAGE_SUCCESS = "New reminder added: %1$s";
    public static final String MESSAGE_DUPLICATE_REMINDER = "This reminder already exists in the list";

    private final Reminder toAdd;

    /**
     * Primary constructor to accept a reminder and add it to reminder list.
     *
     * @param reminder Reminder to add
     */
    public AddReminderCommand(Reminder reminder) {
        requireNonNull(reminder);
        toAdd = reminder;
    }

    /**
     * Main execute method that creates adds a new reminder to the reminder list
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult indicating success or failure of add operation
     * @throws CommandException if {@code Reminder} already exists
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (toAdd.getReminderDate().isBeforeToday()) {
            throw new CommandException(MESSAGE_INVALID_DATE);
        }

        if (model.hasReminder(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_REMINDER);
        }

        model.addReminder(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), TabName.REMINDER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddReminderCommand // instanceof handles nulls
                && toAdd.equals(((AddReminderCommand) other).toAdd));
    }
}
