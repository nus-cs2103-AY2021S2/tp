package seedu.address.logic.uicommands;

import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.uicommands.exceptions.UiCommandException;
import seedu.address.ui.MainWindow;

/**
 * View a panel containing information about a specified project.
 * Ensures that the overview panel is visible.
 */
public class ViewProjectAndOverviewUiCommand extends UiCommand {
    private final Index projectIndex;

    /**
     * Constructs a {@code ViewProjectAndOverviewUiCommand} with the specified {@code projectIndex}.
     *
     * @param projectIndex The {@code projectIndex} of the project to be viewed.
     */
    public ViewProjectAndOverviewUiCommand(Index projectIndex) {
        this.projectIndex = projectIndex;
    }

    @Override
    public void execute(MainWindow mainWindow) throws UiCommandException {
        mainWindow.selectProject(projectIndex);
        mainWindow.displayOverviewTab();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewProjectAndOverviewUiCommand // instanceof handles nulls
                && projectIndex.equals(((ViewProjectAndOverviewUiCommand) other).projectIndex)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectIndex);
    }
}
