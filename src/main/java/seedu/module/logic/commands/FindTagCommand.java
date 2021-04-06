package seedu.module.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.module.commons.core.Messages;
import seedu.module.logic.commands.exceptions.CommandException;
import seedu.module.model.Model;
import seedu.module.model.tag.Tag;
import seedu.module.model.task.Task;

/**
 * Finds tasks associated to an existing tag in the module book.
 */
public class FindTagCommand extends Command {

    public static final String COMMAND_WORD = "findTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Find tasks associated with "
            + "the tag provided and displays them as a list with index numbers.\n"
            + "If multiple tags are found, only the first one will be used.\n"
            + "Only alphanumericals allowed in input. \n"
            + "Parameter(s): TAG \n"
            + "Example: " + COMMAND_WORD + " midterm";

    public static final String MESSAGE_FIND_TAG_TASK_SUCCESS = "Tagged Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to tag must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This tag already exists.";

    private final Tag tag;
    private final Predicate<Task> predicate;


    /**
     * Constructor of FindTagCommand
     *
     * @param tag to search for
     */
    public FindTagCommand(Tag tag) {
        requireNonNull(tag);
        this.tag = tag;
        this.predicate = (Task x) -> x.getTags().contains(tag);
    }

    public Predicate<Task> getPredicate() {
        return predicate;
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

        Predicate<Task> newPred = (Task x) -> containsIgnoreCase(x, tag); //predicate for one task

        model.updateFilteredTaskList(newPred);
        return new CommandResult(String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW,
                model.getFilteredTaskList().size()));
    }

    /**
     * Returns true if tag exists in the task's tags, ignoring case (ie. case-insensitive).
     *
     * @param task
     * @param tag
     * @return boolean
     */
    public boolean containsIgnoreCase(Task task, Tag tag) {
        List<String> strTagsInLower = new ArrayList<String>();
        Set<Tag> tags = task.getTags();
        //convert tags of Task into strings
        for (Tag t : tags) {
            strTagsInLower.add(t.tagName.toLowerCase());
        }
        String target = tag.tagName.toLowerCase();
        if (strTagsInLower.contains(target)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindTagCommand)) {
            return false;
        }

        // state check
        FindTagCommand e = (FindTagCommand) other;
        return tag.equals(e.tag);
    }
}
