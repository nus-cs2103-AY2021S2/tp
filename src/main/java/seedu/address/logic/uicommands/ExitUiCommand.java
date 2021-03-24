package seedu.address.logic.uicommands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.ui.MainWindow;

/**
 * Exits the application.
 */
public class ExitUiCommand extends UiCommand {
    @Override
    public void execute(MainWindow mainWindow) throws CommandException {
        mainWindow.handleExit();
    }
}
