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
 * Deletes a Event identified using it's displayed index from CoLAB.
 */
public class DeleteEventCommand extends Command {

    public static final String COMMAND_WORD = "deleteE";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the event identified by it's index number within the displayed project.\n"
            + "Parameters: PROJECT_INDEX (must be a positive integer)"
            + PREFIX_REMOVE_TASK_INDEX + "EVENT_INDEX \n"
            + "Example: " + COMMAND_WORD + " 1" + " "
            + PREFIX_REMOVE_TASK_INDEX + " 2";

    private final Index projectIndex;
    private final Index targetEventIndex;

    private final Logger logger = LogsCenter.getLogger(DeleteEventCommand.class);

    /**
     * Creates a DeleteEventCommand to delete the specified {@code Event} from {@code Project}.
     * @param projectIndex Index of project that {@code Event} is to be deleted from.
     * @param targetEventIndex Index of event that is to be deleted form {@code Project}.
     */
    public DeleteEventCommand(Index projectIndex, Index targetEventIndex) {
        this.projectIndex = projectIndex;
        this.targetEventIndex = targetEventIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (projectIndex.getZeroBased() >= lastShownList.size()) {
            logger.info("----------------[DeleteEventCommand][Invalid Project Index]");
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        if (targetEventIndex.getZeroBased() >= lastShownList.get(projectIndex.getZeroBased())
                .getEvents().getEvents().size()) {
            logger.info("----------------[DeleteEventCommand][Invalid Event Index]");
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Project projectToEdit = lastShownList.get(projectIndex.getZeroBased());
        requireNonNull(projectToEdit);

        projectToEdit.deleteEvent(targetEventIndex.getZeroBased());
        model.updateFilteredProjectList(Model.PREDICATE_SHOW_ALL_PROJECTS);

        return new CommandResult(String.format(Messages.MESSAGE_DELETE_EVENT_SUCCESS, targetEventIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteEventCommand // instanceof handles nulls
                && projectIndex.equals(((DeleteEventCommand) other).projectIndex))
                && targetEventIndex.equals(((DeleteEventCommand) other).targetEventIndex); // state check
    }

}
