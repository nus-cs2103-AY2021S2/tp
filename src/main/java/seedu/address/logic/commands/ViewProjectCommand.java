package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ViewProjectUiCommand;
import seedu.address.model.Model;
import seedu.address.model.project.Project;

/**
 * View Project in CoLAB.
 */
public class ViewProjectCommand extends Command {

    public static final String COMMAND_WORD = "viewP";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the project identified by the index number used in the displayed projects list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Viewing Project: %s";

    private Index index;

    /**
     * Creates a ViewProjectCommand to view the {@code Project} at the specified {@code index}.
     */
    public ViewProjectCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Project> lastShownList = model.getFilteredProjectList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, lastShownList.get(index.getZeroBased())),
                new ViewProjectUiCommand(index));
    }
}
