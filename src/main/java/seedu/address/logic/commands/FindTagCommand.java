package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.module.commons.core.Messages;
import seedu.module.commons.core.index.Index;
import seedu.module.logic.commands.Command;
import seedu.module.logic.commands.CommandResult;
import seedu.module.logic.commands.exceptions.CommandException;
import seedu.module.model.Model;
import seedu.module.model.tag.Tag;
import seedu.module.model.task.NameContainsKeywordsPredicate;
import seedu.module.model.task.Task;

/**
 * Finds tasks associated to an existing tag in the module book.
 */
public class FindTagCommand extends Command {

    public static final String COMMAND_WORD = "findTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Find tasks associated with "
            + "the tag provided and displays them as a list with index numbers.\n"
            + "If multiple tags are found, only the last one will be added.\n"
            + "Parameters: TAG \n"
            + "Example: " + COMMAND_WORD + " homework"
            + "t/Midterm";

    public static final String MESSAGE_FIND_TAG_TASK_SUCCESS = "Tagged Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to tag must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This tag already exists.";

    private final Tag tag;
    private final Predicate<Task> predicate;


    /**
     * @param tag to search for
     */
    public FindTagCommand(Tag tag) {
        requireNonNull(tag);
        this.tag = tag;
        this.predicate = (Task x) -> x.getTags().contains(tag);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();
        List<Task> listWithTag = new ArrayList<>();

        for (Task t : lastShownList) {
            if (t.getTags().contains(tag)) {
                listWithTag.add(t);
            }
        }
        if (listWithTag.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_NO_TASK_WITH_TAG);
        }

        model.updateFilteredTaskList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.module.logic.commands.FindTagCommand)) {
            return false;
        }

        // state check
        FindTagCommand e = (FindTagCommand) other;
        return tag.equals(e.tag);
    }

    public Tag getTag() {
        return tag;
    }
}
