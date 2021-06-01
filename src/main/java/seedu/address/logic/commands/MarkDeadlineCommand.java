package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MARK_TASK_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ViewProjectAndOverviewUiCommand;
import seedu.address.model.Model;
import seedu.address.model.project.Project;

/**
 * Marks a Deadline as done. The Deadline is identified using it's displayed index from CoLAB.
 */
public class MarkDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "markD";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the deadline identified by the index number used within the displayed project as done.\n"
            + "Parameters: PROJECT_INDEX"
            + PREFIX_MARK_TASK_INDEX + "DEADLINE_INDEX \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MARK_TASK_INDEX + " 1";

    public static final String MESSAGE_ALREADY_MARKED_DEADLINE = "This deadline has already been marked as done.";

    private final Index projectIndex;
    private final Index targetDeadlineIndex;

    /**
     * Creates a MarkDeadlineCommand to mark the specified {@code Deadline} from {@code Project} as done.
     *
     * @param projectIndex Index of project in which {@code Deadline} is to be marked as done.
     * @param targetDeadlineIndex Index of deadline in {@code Project} that is to be marked as done.
     */
    public MarkDeadlineCommand(Index projectIndex, Index targetDeadlineIndex) {
        this.projectIndex = projectIndex;
        this.targetDeadlineIndex = targetDeadlineIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (projectIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        if (targetDeadlineIndex.getZeroBased() >= lastShownList.get(projectIndex.getZeroBased())
                .getSortedDeadlines().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DEADLINE_DISPLAYED_INDEX);
        }

        Project projectToEdit = lastShownList.get(projectIndex.getZeroBased());
        requireNonNull(projectToEdit);

        if (projectToEdit.getSortedDeadlines().get(targetDeadlineIndex.getZeroBased()).getIsDone()) {
            throw new CommandException(MESSAGE_ALREADY_MARKED_DEADLINE);
        }

        projectToEdit.markDeadline(targetDeadlineIndex.getZeroBased());
        model.updateFilteredProjectList(Model.PREDICATE_SHOW_ALL_PROJECTS);

        return new CommandResult(String.format(Messages.MESSAGE_MARK_DEADLINE_SUCCESS, targetDeadlineIndex
                .getOneBased()), new ViewProjectAndOverviewUiCommand(projectIndex));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkDeadlineCommand // instanceof handles nulls
                && projectIndex.equals(((MarkDeadlineCommand) other).projectIndex))
                && targetDeadlineIndex.equals(((MarkDeadlineCommand) other).targetDeadlineIndex); // state check
    }

}
