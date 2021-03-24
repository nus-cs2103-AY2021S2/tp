package seedu.address.logic.uicommands;

import seedu.address.logic.uicommands.exceptions.UiCommandException;
import seedu.address.ui.MainWindow;

/**
 * Show todos tab of a Project.
 */
public class ShowTodosTabUiCommand extends UiCommand {
    @Override
    public void execute(MainWindow mainWindow) throws UiCommandException {
        mainWindow.handleShowTodosTab();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ShowTodosTabUiCommand; // instanceof handles null
    }
}
