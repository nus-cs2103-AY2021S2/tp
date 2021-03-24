package seedu.address.logic.uicommands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.ui.MainWindow;

/**
 * Show todos tab of a Project.
 */
public class ShowTodosTabUiCommand extends UiCommand {
    @Override
    public void execute(MainWindow mainWindow) throws CommandException {
        mainWindow.handleShowTodosTab();
    }
}
