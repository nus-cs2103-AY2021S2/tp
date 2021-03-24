package seedu.address.logic.uicommands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.ui.MainWindow;

/**
 * View a panel containing information about a specified project.
 */
public class ViewProjectUiCommand extends UiCommand {
    private final Index projectIndex;

    /**
     * Constructs a {@code ViewProjectUiCommand} with the specified {@code projectIndex}.
     *
     * @param projectIndex The {@code projectIndex} of the project to be viewed.
     */
    public ViewProjectUiCommand(Index projectIndex) {
        this.projectIndex = projectIndex;
    }

    @Override
    public void execute(MainWindow mainWindow) throws CommandException {
        mainWindow.handleSelectProject(projectIndex);
    }
}
