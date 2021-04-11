package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ShowTodayUiCommand;
import seedu.address.model.Model;
import seedu.address.model.project.Project;

/**
 * Deletes a project identified using it's displayed index from the project list.
 */
public class DeleteProjectCommand extends Command {

    public static final String COMMAND_WORD = "deleteP";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the project identified by the index number used in the side panel.\n"
            + "Parameters: PROJECT_INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_DELETE_PROJECT_SUCCESS = "Deleted project: %1$s";

    private final Index targetIndex;

    public DeleteProjectCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Project projectToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteProject(projectToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PROJECT_SUCCESS, projectToDelete.getProjectName()),
                new ShowTodayUiCommand());
    }

    @Override
    public boolean equals(Object other) {
        return other == this //short circuit if same project
                || (other instanceof DeleteProjectCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteProjectCommand) other).targetIndex));
    }
}
