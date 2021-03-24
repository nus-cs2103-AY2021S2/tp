package seedu.address.logic.uicommands;

import seedu.address.logic.uicommands.exceptions.UiCommandException;
import seedu.address.ui.MainWindow;

/**
 * Exits the application.
 */
public class ExitUiCommand extends UiCommand {
    @Override
    public void execute(MainWindow mainWindow) throws UiCommandException {
        mainWindow.handleExit();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ExitUiCommand; // instanceof handles null
    }
}
