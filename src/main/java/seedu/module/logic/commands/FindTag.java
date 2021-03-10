package seedu.module.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.module.commons.util.CollectionUtil.requireAllNonNull;
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
 * Finds tasks associated to an existing tag in the module book.
 */
public class FindTag extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a tag to task identified "
            + "by the index number used in the last person listing. "
            + "If multiple tags are found, only the last one will be added.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "t/[TAG]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "t/Midterm";

    public static final String MESSAGE_TAG_TASK_SUCCESS = "Tagged Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to tag must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This tag already exists.";

    private final Tag tag;

    /**
     * @param tag to search for
     */
    public FindTag(Tag tag) {
        requireNonNull(tag);
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();
        List<Task> listWithTag = new List<>();

        for (Task t : lastShownList) {
            if (t.getTags().contains(tag)) {
                listWithTag.add(t);
            }
        }
        //work up till here
        Task taskToTag = lastShownList.get(index.getZeroBased());
        Set<Tag> oldTags = taskToTag.getTags();
        Set<Tag> newTags = addTags(oldTags, this.tag);
        Task editedTask = new Task(taskToTag.getName(), taskToTag.getDeadline(),
                taskToTag.getModule(), taskToTag.getDescription(), newTags);

        if (!taskToTag.isSameTask(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(taskToTag, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_TAG_TASK_SUCCESS, editedTask));
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
                && tag.equals(e.tag);
    }
}
