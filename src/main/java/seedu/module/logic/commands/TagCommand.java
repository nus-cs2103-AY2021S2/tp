package seedu.module.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.module.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.module.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.module.commons.core.Messages;
import seedu.module.commons.core.index.Index;
import seedu.module.logic.commands.exceptions.CommandException;
import seedu.module.model.Model;
import seedu.module.model.tag.Tag;
import seedu.module.model.task.Task;

/**
 * Add a tag to an existing task in the module book.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add one or multiple tags to task identified "
            + "by the index number used in the last person listing.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TAG + "TAG "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "t/Midterm";

    public static final String MESSAGE_TAG_TASK_SUCCESS = "Tagged Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to tag must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This tag already exists.";

    private final Index index;
    private Set<Tag> tags;

    /**
     * @param index of the task in the filtered task list to edit the remark
     */
    public TagCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToTag = lastShownList.get(index.getZeroBased());
        Set<Tag> oldTags = taskToTag.getTags();
        Set<Tag> newTags = addTags(oldTags, this.tags);
        Task editedTask = new Task(taskToTag.getName(), taskToTag.getDeadline(),
                taskToTag.getModule(), taskToTag.getDescription(), taskToTag.getWorkload(),
                taskToTag.getDoneStatus(), newTags);

        if (!taskToTag.isSameTask(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(taskToTag, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_TAG_TASK_SUCCESS, editedTask));
    }

    /**
     * Add tags to set of old tags, duplicated tag will not be added
     *
     * @param oldTags set of old tags
     * @param newTags new Tags to be added
     * @return set of new tags
     */
    private Set<Tag> addTags(Set<Tag> oldTags, Set<Tag> newTags) {
        Set<Tag> resultTags = new HashSet<>(oldTags);
        resultTags.addAll(newTags);
        return resultTags;
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     *
     * @param tags the set of tags to be set
     */
    public void setTags(Set<Tag> tags) {
        this.tags = (tags != null)
                ? new HashSet<>(tags)
                : null;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagCommand)) {
            return false;
        }

        // state check
        TagCommand e = (TagCommand) other;
        return index.equals(e.index)
                && tags.equals(e.tags);
    }
}
