package seedu.address.logic.uicommands;

import seedu.address.logic.uicommands.exceptions.UiCommandException;
import seedu.address.ui.MainWindow;

/**
 * Show overview tab of a Project.
 */
public class ShowOverviewTabUiCommand extends UiCommand {
    @Override
    public void execute(MainWindow mainWindow) throws UiCommandException {
        mainWindow.handleShowOverviewTab();
    }
}
