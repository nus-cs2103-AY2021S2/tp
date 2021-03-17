package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Finds and lists all tasks in planner whose title contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose titles contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " quiz meeting task742";

    public static final String SHORT_MESSAGE_USAGE = COMMAND_WORD + " KEYWORD [MORE_KEYWORDS]...\n";

    public static final String TAG_USAGE = "Please enter valid input field to find tasks by tag(s) "
            + "in correct format as follows:" + COMMAND_WORD + " t/ [TAG_NAME]";

    public static final String DESCRIPTION_USAGE = "Please enter valid input field to find tasks by description "
            + "in correct format as follows:" + COMMAND_WORD + " d/ [DESCRIPTION_NAME]";

    public static final String MULTIPLE_COMMANDS = "Multiple commands detected !!! For find by title query, "
            + "please do not include search by tag (t/) or description (d/). For find by description (d/), "
            + "please do not include any tag search (t/) and only one description search is allowed. "
            + "For find by tag (t/), multiple tags search are allowed but do not include any description search (d/).";

    private final Predicate<Task> predicate;

    public FindCommand(Predicate<Task> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
