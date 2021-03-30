package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRINGSCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;
import static seedu.address.model.task.RecurringSchedule.INVALID_ENDDATE;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.conditions.ConditionManager;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.Description;
import seedu.address.model.task.Duration;
import seedu.address.model.task.RecurringSchedule;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

/**
 * Edits the details of an existing task in the planner.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of a task. \n\n "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_DURATION + "15:30-18:30] "
            + "[" + PREFIX_RECURRINGSCHEDULE + "RECURRINGSCHEDULE] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "91234567 ";

    public static final String SHORT_MESSAGE_USAGE = COMMAND_WORD + " "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_DURATION + "15:30-18:30] "
            + "[" + PREFIX_RECURRINGSCHEDULE + "RECURRINGSCHEDULE] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TAG + "TAG]...\n";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the planner.";

    private final Logger logger = LogsCenter.getLogger(getClass());

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
        int indexValue = index.getZeroBased();

        checkForValidIndex(indexValue, lastShownList);

        Task taskToEdit = lastShownList.get(indexValue);
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        checkForDuplicateTask(model, taskToEdit, editedTask);
        checkForInvalidDate(editedTask);
        ConditionManager.enforceAttributeConstraints(editedTask);

        editedTask = handleTagUpdates(model, taskToEdit, editedTask);
        updateModel(model, taskToEdit, editedTask);

        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    private void checkForValidIndex(int indexValue, List<Task> lastShownList) throws CommandException {
        boolean isInvalidIndex = indexValue >= lastShownList.size();

        if (isInvalidIndex) {
            logger.info("Invalid Index detected: " + Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

    private void checkForDuplicateTask(Model model, Task taskToEdit, Task editedTask) throws CommandException {
        boolean isDuplicateTask = !taskToEdit.isSameTask(editedTask) && model.hasTask(editedTask);
        if (isDuplicateTask) {
            logger.info("Duplicate task detected: " + MESSAGE_DUPLICATE_TASK);
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
    }

    private void checkForInvalidDate(Task editedTask) throws CommandException {
        if (editedTask.hasExpired()) {
            logger.info("Invalid date detected: " + INVALID_ENDDATE);
            throw new CommandException(INVALID_ENDDATE);
        }
    }

    private Task handleTagUpdates(Model model, Task taskToEdit, Task editedTask) throws CommandException {
        Set<Tag> oldTags = taskToEdit.getTags();
        Set<Tag> newTags = editedTask.getTags();
        model.setTags(oldTags, newTags);
        return editedTask.setTags(newTags);
    }

    private void updateModel(Model model, Task taskToEdit, Task editedTask) throws CommandException {
        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        Title updatedTitle = editTaskDescriptor.getTitle().orElse(taskToEdit.getTitle());
        Date updatedDate = editTaskDescriptor.getDate().orElse(taskToEdit.getDate());
        Duration updatedDuration = editTaskDescriptor.getDuration().orElse(taskToEdit.getDuration());
        RecurringSchedule updatedRecurringSchedule = editTaskDescriptor.getRecurringSchedule()
                .orElse(taskToEdit.getRecurringSchedule());
        Description updatedDescription = editTaskDescriptor.getDescription().orElse(taskToEdit.getDescription());
        Status updatedStatus = editTaskDescriptor.getStatus().orElse(taskToEdit.getStatus());
        Set<Tag> updatedTags = editTaskDescriptor.getTags().orElse(taskToEdit.getTags());

        return new Task(updatedTitle, updatedDate, updatedDuration, updatedRecurringSchedule, updatedDescription,
                updatedStatus, updatedTags);
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
        private Title title;
        private Date date;
        private Duration duration;
        private RecurringSchedule recurringSchedule;
        private Description description;
        private Status status;
        private Set<Tag> tags;

        public EditTaskDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setTitle(toCopy.title);
            setDate(toCopy.date);
            setDuration(toCopy.duration);
            setRecurringSchedule(toCopy.recurringSchedule);
            setDescription(toCopy.description);
            setStatus(toCopy.status);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, date, duration, recurringSchedule,
                    description, status, tags);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        public Optional<Duration> getDuration() {
            return Optional.ofNullable(duration);
        }

        public void setRecurringSchedule(RecurringSchedule recurringSchedule) {
            this.recurringSchedule = recurringSchedule;
        }

        public Optional<RecurringSchedule> getRecurringSchedule() {
            return Optional.ofNullable(recurringSchedule);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
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

            return getTitle().equals(e.getTitle())
                    && getDate().equals(e.getDate())
                    && getRecurringSchedule().equals(e.getRecurringSchedule())
                    && getDescription().equals(e.getDescription())
                    && getStatus().equals(e.getStatus())
                    && getTags().equals(e.getTags());
        }
    }
}
