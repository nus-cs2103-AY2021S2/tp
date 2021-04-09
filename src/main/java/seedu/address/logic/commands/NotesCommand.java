package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Notes;
import seedu.address.model.person.Task;

public class NotesCommand extends Command {

    public static final String COMMAND_WORD = "notes";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the notes of the task identified "
        + "by the index number used in the All tasks list. "
        + "Existing notes will be overwritten by the input.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + PREFIX_NOTES + "REMARK\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_NOTES + "Likes to swim.";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Notes: %2$s";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added notes to Person: %1$s";

    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed notes from Person: %1$s";

    private final Index index;
    private final Notes notes;

    /**
     * @param index  of the person in the filtered person list to edit the notes
     * @param notes of the person to be updated to
     */
    public NotesCommand(Index index, Notes notes) {
        requireAllNonNull(index, notes);

        this.index = index;
        this.notes = notes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, NotesCommand.MESSAGE_USAGE));
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = new Task(taskToEdit.getTaskName(), taskToEdit.getModuleCode(),
                taskToEdit.getDeadlineDate(), taskToEdit.getDeadlineTime(),
                taskToEdit.getStatus(), taskToEdit.getWeightage(),
                notes, taskToEdit.getTags(), taskToEdit.getPriorityTag());

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        model.refreshDailyTasks(taskToEdit, editedTask);

        return new CommandResult(generateSuccessMessage(editedTask));
    }

    /**
     * Generates a command execution success message based on whether the notes is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Task taskToEdit) {
        String message = !notes.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, taskToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles null
        if (!(other instanceof NotesCommand)) {
            return false;
        }

        // state check
        NotesCommand e = (NotesCommand) other;
        return index.equals(e.index) && notes.equals(e.notes);
    }
}
