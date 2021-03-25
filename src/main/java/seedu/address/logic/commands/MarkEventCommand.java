package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MARK_TASK_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;

/**
 * Marks a Event as done. The Event is identified using it's displayed index from CoLAB.
 */
public class MarkEventCommand extends Command {

    public static final String COMMAND_WORD = "markE";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the event identified by it's index number as done within the displayed project.\n"
            + "Parameters: PROJECT_INDEX (must be a positive integer)"
            + PREFIX_MARK_TASK_INDEX + "EVENT_INDEX \n"
            + "Example: " + COMMAND_WORD + " 1" + " "
            + PREFIX_MARK_TASK_INDEX + " 2";

    private final Index projectIndex;
    private final Index targetEventIndex;

    /**
     * Creates a MarkEventCommand to mark the specified {@code Event} from {@code Project} as done.
     *
     * @param projectIndex Index of project in which {@code Event} is to be marked as done.
     * @param targetEventIndex Index of event in {@code Project} that is to be marked as done.
     */
    public MarkEventCommand(Index projectIndex, Index targetEventIndex) {
        this.projectIndex = projectIndex;
        this.targetEventIndex = targetEventIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (projectIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        if (targetEventIndex.getZeroBased() >= lastShownList.get(projectIndex.getZeroBased())
                .getEvents().getEvents().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Project projectToEdit = lastShownList.get(projectIndex.getZeroBased());
        requireNonNull(projectToEdit);

        projectToEdit.markEvent(targetEventIndex.getZeroBased());
        model.updateFilteredProjectList(Model.PREDICATE_SHOW_ALL_PROJECTS);

        return new CommandResult(String.format(Messages.MESSAGE_MARK_EVENT_SUCCESS, targetEventIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkEventCommand // instanceof handles nulls
                && projectIndex.equals(((MarkEventCommand) other).projectIndex))
                && targetEventIndex.equals(((MarkEventCommand) other).targetEventIndex); // state check
    }

}
