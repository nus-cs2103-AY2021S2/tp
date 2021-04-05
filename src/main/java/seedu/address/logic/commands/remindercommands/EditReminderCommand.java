package seedu.address.logic.commands.remindercommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE;
import static seedu.address.commons.core.Messages.MESSAGE_UNABLE_TO_EDIT_PAST_REMINDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_REMINDER;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.common.Description;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderDate;

/**
 * Edits the details of an existing reminder in the reminder list.
 */
public class EditReminderCommand extends Command {

    public static final String COMMAND_WORD = "edit_reminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the reminder identified "
            + "by the index number used in the displayed reminder list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_DATE + "REMINDER DATE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "Bring Science Textbook "
            + PREFIX_DATE + "2021-3-30";

    public static final String MESSAGE_EDIT_REMINDER_SUCCESS = "Edited Reminder: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_REMINDER = "This reminder already exists.";

    private final Index index;
    private final EditReminderDescriptor editReminderDescriptor;

    /**
     * @param index of the reminder in the filtered reminder list to edit
     */
    public EditReminderCommand(Index index, EditReminderDescriptor editReminderDescriptor) {
        requireNonNull(index);
        requireNonNull(editReminderDescriptor);

        this.index = index;
        this.editReminderDescriptor = editReminderDescriptor;
    }

    /**
     * Creates and returns a {@code Reminder} with the details of {@code reminderToEdit}
     * edited with {@code editReminderDescriptor}.
     */
    private static Reminder createEditedReminder(Reminder reminderToEdit,
                                                 EditReminderDescriptor editReminderDescriptor) {
        assert reminderToEdit != null;

        Description updatedDescription =
                editReminderDescriptor.getDescription().orElse(reminderToEdit.getDescription());
        ReminderDate updatedReminderDate =
                editReminderDescriptor.getReminderDate().orElse(reminderToEdit.getReminderDate());

        return new Reminder(updatedDescription, updatedReminderDate);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Reminder> lastShownList = model.getFilteredReminderList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX);
        }

        Reminder reminderToEdit = lastShownList.get(index.getZeroBased());
        Reminder editedReminder = createEditedReminder(reminderToEdit, editReminderDescriptor);

        if (reminderToEdit.getReminderDate().isBeforeToday()) {
            throw new CommandException(MESSAGE_UNABLE_TO_EDIT_PAST_REMINDER);
        }

        if (editedReminder.getReminderDate().isBeforeToday()) {
            throw new CommandException(MESSAGE_INVALID_DATE);
        }

        if (!reminderToEdit.equals(editedReminder) && model.hasReminder(editedReminder)) {
            throw new CommandException(MESSAGE_DUPLICATE_REMINDER);
        }

        model.setReminder(reminderToEdit, editedReminder);
        model.updateFilteredReminderList(PREDICATE_SHOW_ALL_REMINDER);
        return new CommandResult(String.format(MESSAGE_EDIT_REMINDER_SUCCESS, editedReminder), TabName.REMINDER);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditReminderCommand)) {
            return false;
        }

        // state check
        EditReminderCommand e = (EditReminderCommand) other;
        return index.equals(e.index) && editReminderDescriptor.equals(e.editReminderDescriptor);
    }

    /**
     * Stores the details to edit the reminder with. Each non-empty field value will replace the
     * corresponding field value of the reminder.
     */
    public static class EditReminderDescriptor {
        private Description description;
        private ReminderDate reminderDate;

        public EditReminderDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditReminderDescriptor(EditReminderDescriptor toCopy) {
            setReminderDate(toCopy.reminderDate);
            setDescription(toCopy.description);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, reminderDate);
        }

        public Optional<ReminderDate> getReminderDate() {
            return Optional.ofNullable(this.reminderDate);
        }

        public void setReminderDate(ReminderDate reminderDate) {
            this.reminderDate = reminderDate;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditReminderDescriptor)) {
                return false;
            }

            // state check
            EditReminderDescriptor e = (EditReminderDescriptor) other;

            return getReminderDate().equals(e.getReminderDate())
                    && getDescription().equals(e.getDescription());
        }
    }
}
