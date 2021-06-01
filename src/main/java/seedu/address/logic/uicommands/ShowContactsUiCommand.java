package seedu.address.logic.uicommands;

import seedu.address.logic.uicommands.exceptions.UiCommandException;
import seedu.address.ui.MainWindow;

/**
 * Open the contacts panel.
 */
public class ShowContactsUiCommand extends UiCommand {
    @Override
    public void execute(MainWindow mainWindow) throws UiCommandException {
        mainWindow.displayContacts();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ShowContactsUiCommand; // instanceof handles null
    }
}
