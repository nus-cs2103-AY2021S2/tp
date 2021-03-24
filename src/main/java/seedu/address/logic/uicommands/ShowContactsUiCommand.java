package seedu.address.logic.uicommands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.ui.MainWindow;

/**
 * Open the contacts panel.
 */
public class ShowContactsUiCommand extends UiCommand {
    @Override
    public void execute(MainWindow mainWindow) throws CommandException {
        mainWindow.handleDisplayContacts();
    }
}
