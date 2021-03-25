package seedu.address.logic.uicommands;

import seedu.address.logic.uicommands.exceptions.UiCommandException;
import seedu.address.ui.MainWindow;

/**
 * Shows the today panel.
 */
public class ShowTodayUiCommand extends UiCommand {
    @Override
    public void execute(MainWindow mainWindow) throws UiCommandException {
        mainWindow.displayToday();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ShowTodayUiCommand; // instanceof handles null
    }
}
