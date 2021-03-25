package seedu.address.logic.uicommands;

import seedu.address.logic.uicommands.exceptions.UiCommandException;
import seedu.address.ui.MainWindow;

/**
 * Represents a UI command with hidden internal logic and the ability to be executed.
 */
public abstract class UiCommand {

    /**
     * Executes the Ui command and returns the result message.
     *
     * @param mainWindow {@code MainWindow} which the command should operate on.
     * @throws UiCommandException If an error occurs during UI command execution.
     */
    public abstract void execute(MainWindow mainWindow) throws UiCommandException;

}

