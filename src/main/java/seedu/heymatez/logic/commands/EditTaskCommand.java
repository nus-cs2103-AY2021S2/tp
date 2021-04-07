package seedu.heymatez.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_ASSIGNEE;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.heymatez.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.heymatez.commons.core.index.Index;
import seedu.heymatez.commons.util.CollectionUtil;
import seedu.heymatez.logic.commands.exceptions.CommandException;
import seedu.heymatez.model.Model;
import seedu.heymatez.model.assignee.Assignee;
import seedu.heymatez.model.task.Deadline;
import seedu.heymatez.model.task.Description;
import seedu.heymatez.model.task.Priority;
import seedu.heymatez.model.task.Task;
import seedu.heymatez.model.task.TaskStatus;
import seedu.heymatez.model.task.Title;

/**
 * Edits the details of an existing task in HeyMatez.
 */
public class EditTaskCommand extends Command {
    public static final String COMMAND_WORD = "editTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the displayed task board. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "NEW_TITLE] "
            + "[" + PREFIX_DESCRIPTION + "NEW_DESCRIPTION] "
            + "[" + PREFIX_DEADLINE + "NEW_DEADLINE] "
            + "[" + PREFIX_STATUS + "NEW_STATUS] "
            + "[" + PREFIX_PRIORITY + "NEW_PRIORITY] "
            + "[" + PREFIX_ASSIGNEE + "NEW_ASSIGNEE]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TITLE + "Plan open house meeting "
            + PREFIX_STATUS + "completed";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private final EditTaskCommand.EditTaskDescriptor editTaskDescriptor;

    /**
     * @param index of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditTaskCommand(Index index, EditTaskCommand.EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = new EditTaskCommand.EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        if (!model.checkAssignees(editedTask)) {
            throw new CommandException(Assignee.MESSAGE_CONSTRAINTS);
        }

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(Task taskToEdit, EditTaskCommand.EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        Title updatedTitle = editTaskDescriptor.getTitle().orElse(taskToEdit.getTitle());
        Description updatedDescription = editTaskDescriptor.getDescription().orElse(taskToEdit.getDescription());
        Deadline updatedDeadline = editTaskDescriptor.getDeadline().orElse(taskToEdit.getDeadline());
        TaskStatus updatedStatus = editTaskDescriptor.getStatus().orElse(taskToEdit.getTaskStatus());
        Priority updatedPriority = editTaskDescriptor.getPriority().orElse(taskToEdit.getPriority());
        Set<Assignee> updatedAssignees = editTaskDescriptor.getAssignees().orElse(taskToEdit.getAssignees());

        return new Task(updatedTitle, updatedDescription, updatedDeadline, updatedStatus, updatedPriority,
                updatedAssignees);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTaskCommand)) {
            return false;
        }

        // state check
        EditTaskCommand e = (EditTaskCommand) other;
        System.out.println(index);
        System.out.println(e.index);
        return index.equals(e.index)
                && editTaskDescriptor.equals(e.editTaskDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditTaskDescriptor {
        private Title title;
        private Description description;
        private TaskStatus status;
        private Deadline deadline;
        private Priority priority;
        private Set<Assignee> assignees;

        public EditTaskDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code assignees} is used internally.
         */
        public EditTaskDescriptor(EditTaskCommand.EditTaskDescriptor toCopy) {
            setTitle(toCopy.title);
            setDescription(toCopy.description);
            setStatus(toCopy.status);
            setDeadline(toCopy.deadline);
            setPriority(toCopy.priority);
            setAssignees(toCopy.assignees);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, description, status, deadline, priority, assignees);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setStatus(TaskStatus status) {
            this.status = status;
        }

        public Optional<TaskStatus> getStatus() {
            return Optional.ofNullable(status);
        }

        public void setDeadline(Deadline date) {
            this.deadline = date;
        }

        public Optional<Deadline> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setPriority(Priority priority) {
            this.priority = priority;
        }

        public Optional<Priority> getPriority() {
            return Optional.ofNullable(priority);
        }

        /**
         * Sets {@code assignees} to this object's {@code assignees}.
         * A defensive copy of {@code assignees} is used internally.
         */
        public void setAssignees(Set<Assignee> assignees) {
            this.assignees = (assignees != null) ? new HashSet<>(assignees) : null;
        }

        /**
         * Returns an unmodifiable assignees set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code assignees} is null.
         */
        public Optional<Set<Assignee>> getAssignees() {
            return (assignees != null) ? Optional.of(Collections.unmodifiableSet(assignees)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTaskCommand.EditTaskDescriptor)) {
                return false;
            }

            // state check
            EditTaskCommand.EditTaskDescriptor e = (EditTaskCommand.EditTaskDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getDescription().equals(e.getDescription())
                    && getStatus().equals(e.getStatus())
                    && getDeadline().equals(e.getDeadline())
                    && getPriority().equals(e.getPriority())
                    && getAssignees().equals(e.getAssignees());
        }
    }
}
