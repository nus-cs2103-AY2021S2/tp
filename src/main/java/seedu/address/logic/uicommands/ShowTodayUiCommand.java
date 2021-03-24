package seedu.address.logic.uicommands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.ui.MainWindow;

/**
 * Shows the today panel.
 */
public class ShowTodayUiCommand extends UiCommand {
    @Override
    public void execute(MainWindow mainWindow) throws CommandException {
        mainWindow.handleDisplayToday();
    }
}
