package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRINGSCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.model.task.RecurringSchedule.INVALID_ENDDATE;

import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Adds a task to the planner.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the planner. \n\n"
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + "[" + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_DURATION + "DURATION "
            + PREFIX_RECURRINGSCHEDULE + "RECURRINGSCHEDULE "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_TAG + "TAG]...\n\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "This is a task "
            + PREFIX_DEADLINE + "12 Oct 2012 "
            + PREFIX_DURATION + "15:30"
            + PREFIX_RECURRINGSCHEDULE + "[10/05/2021][Mon][weekly] "
            + PREFIX_DESCRIPTION + "This is the task's description "
            + PREFIX_TAG + "tag1 "
            + PREFIX_TAG + "tag2";

    public static final String SHORT_MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_TITLE + "TITLE "
            + "[" + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_DURATION + "DURATION "
            + PREFIX_RECURRINGSCHEDULE + "RECURRINGSCHEDULE "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_TAG + "TAG]\n";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the planner";

    private Task toAdd;

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Creates an AddCommand to add the specified {@code Task}
     */
    public AddCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        boolean isDuplicateTask = model.hasTask(toAdd);
        if (isDuplicateTask) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        if (toAdd.hasExpired()) {
            logger.info("Invalid end date in recurring schedule detected: " + INVALID_ENDDATE);
            throw new CommandException(INVALID_ENDDATE);
        }

        Set<Tag> tagSet = toAdd.getTags();
        Set<Tag> newTagSet = model.addTagsIfAbsent(tagSet);
        toAdd = toAdd.setTags(newTagSet);

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
