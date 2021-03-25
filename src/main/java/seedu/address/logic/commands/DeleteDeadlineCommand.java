package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMOVE_TASK_INDEX;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;

/**
 * Deletes a Deadline identified using it's displayed index from CoLAB.
 */
public class DeleteDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deleteD";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the deadline identified by it's index number within the displayed project.\n"
            + "Parameters: PROJECT_INDEX (must be a positive integer)"
            + PREFIX_REMOVE_TASK_INDEX + "DEADLINE_INDEX \n"
            + "Example: " + COMMAND_WORD + " 1" + " "
            + PREFIX_REMOVE_TASK_INDEX + " 2";

    private final Index projectIndex;
    private final Index targetDeadlineIndex;

    private final Logger logger = LogsCenter.getLogger(DeleteDeadlineCommand.class);

    /**
     * Creates a DeleteDeadlineCommand to delete the specified {@code Deadline} from {@code Project}.
     * @param projectIndex Index of project that {@code Deadline} is to be deleted from.
     * @param targetDeadlineIndex Index of Deadline that is to be deleted form {@code Project}.
     */
    public DeleteDeadlineCommand(Index projectIndex, Index targetDeadlineIndex) {
        this.projectIndex = projectIndex;
        this.targetDeadlineIndex = targetDeadlineIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (projectIndex.getZeroBased() >= lastShownList.size()) {
            logger.info("----------------[DeleteDeadlineCommand][Invalid Project Index]");
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        if (targetDeadlineIndex.getZeroBased() >= lastShownList.get(projectIndex.getZeroBased())
                .getDeadlines().getDeadlines().size()) {
            logger.info("----------------[DeleteDeadlineCommand][Invalid Deadline Index]");
            throw new CommandException(Messages.MESSAGE_INVALID_DEADLINE_DISPLAYED_INDEX);
        }

        Project projectToEdit = lastShownList.get(projectIndex.getZeroBased());
        requireNonNull(projectToEdit);

        projectToEdit.deleteDeadline(targetDeadlineIndex.getZeroBased());
        model.updateFilteredProjectList(Model.PREDICATE_SHOW_ALL_PROJECTS);

        return new CommandResult(String.format(Messages.MESSAGE_DELETE_DEADLINE_SUCCESS,
                targetDeadlineIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteDeadlineCommand // instanceof handles nulls
                && projectIndex.equals(((DeleteDeadlineCommand) other).projectIndex))
                && targetDeadlineIndex.equals(((DeleteDeadlineCommand) other).targetDeadlineIndex); // state check
    }

}
