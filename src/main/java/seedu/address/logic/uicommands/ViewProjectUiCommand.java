package seedu.address.logic.uicommands;

import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.uicommands.exceptions.UiCommandException;
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
    public void execute(MainWindow mainWindow) throws UiCommandException {
        mainWindow.handleSelectProject(projectIndex);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewProjectUiCommand // instanceof handles nulls
                && projectIndex.equals(((ViewProjectUiCommand) other).projectIndex)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectIndex);
    }
}
