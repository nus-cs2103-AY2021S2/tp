package seedu.module.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.module.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.module.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.module.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.module.logic.parser.CliSyntax.PREFIX_WORKLOAD;
import static seedu.module.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.module.commons.core.Messages;
import seedu.module.commons.core.index.Index;
import seedu.module.commons.util.CollectionUtil;
import seedu.module.logic.commands.exceptions.CommandException;
import seedu.module.model.Model;
import seedu.module.model.tag.Tag;
import seedu.module.model.task.Time;
import seedu.module.model.task.Description;
import seedu.module.model.task.DoneStatus;
import seedu.module.model.task.Module;
import seedu.module.model.task.Name;
import seedu.module.model.task.Task;
import seedu.module.model.task.Workload;

/**
 * Edits the details of an existing task in the module book except for DoneStatus.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the displayed task list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TASK_NAME + "NAME] "
            + "[" + PREFIX_DEADLINE + "DEADLINE] "
            + "[" + PREFIX_MODULE + "MODULE] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_WORKLOAD + "WORKLOAD] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DEADLINE + "2021-03-15 "
            + PREFIX_MODULE + "CS2103T";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the module book.";

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * @param index of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        if (!taskToEdit.isSameTask(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        Name updatedName = editTaskDescriptor.getName().orElse(taskToEdit.getName());
        Time updatedStartTime = editTaskDescriptor.getStartTime().orElse(taskToEdit.getStartTime());
        Time updatedDeadline = editTaskDescriptor.getDeadline().orElse(taskToEdit.getDeadline());
        Module updatedModule = editTaskDescriptor.getModule().orElse(taskToEdit.getModule());
        Description updatedDescription = editTaskDescriptor.getDescription().orElse(taskToEdit.getDescription());
        Workload updatedWorkload = editTaskDescriptor.getWorkload().orElse(taskToEdit.getWorkload());
        DoneStatus originalDoneStatus = taskToEdit.getDoneStatus();
        Set<Tag> updatedTags = editTaskDescriptor.getTags().orElse(taskToEdit.getTags());

        if (updatedStartTime == null) {
            return new Task(updatedName, updatedDeadline, updatedModule, updatedDescription,
                    updatedWorkload, originalDoneStatus, updatedTags);
        } else {
            return new Task(updatedName, updatedStartTime, updatedDeadline, updatedModule, updatedDescription,
                    updatedWorkload, originalDoneStatus, updatedTags);
        }

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editTaskDescriptor.equals(e.editTaskDescriptor);
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private Name name;
        private Time startTime;
        private Time deadline;
        private Module module;
        private Description description;
        private Workload workload;
        private Set<Tag> tags;

        public EditTaskDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setName(toCopy.name);
            setStartTime(toCopy.startTime);
            setDeadline(toCopy.deadline);
            setModule(toCopy.module);
            setDescription(toCopy.description);
            setWorkload(toCopy.workload);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, startTime, deadline, module, description, workload, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setStartTime(Time startTime) {
            this.startTime = startTime;
        }

        public Optional<Time> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setDeadline(Time deadline) {
            this.deadline = deadline;
        }

        public Optional<Time> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setModule(Module module) {
            this.module = module;
        }

        public Optional<Module> getModule() {
            return Optional.ofNullable(module);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setWorkload(Workload workload) {
            this.workload = workload;
        }

        public Optional<Workload> getWorkload() {
            return Optional.ofNullable(workload);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTaskDescriptor)) {
                return false;
            }

            // state check
            EditTaskDescriptor e = (EditTaskDescriptor) other;

            return getName().equals(e.getName())
                    && getStartTime().equals(e.getStartTime())
                    && getDeadline().equals(e.getDeadline())
                    && getModule().equals(e.getModule())
                    && getDescription().equals(e.getDescription())
                    && getWorkload().equals(e.getWorkload())
                    && getTags().equals(e.getTags());
        }
    }
}
