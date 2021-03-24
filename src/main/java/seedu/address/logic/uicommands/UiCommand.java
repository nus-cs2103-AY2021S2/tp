package seedu.address.logic.uicommands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.ui.MainWindow;

//public enum UiCommand {
//    NONE,
//    OPEN_HELP_WINDOW,
//}

/**
 * Represents a UI command with hidden internal logic and the ability to be executed.
 */
public abstract class UiCommand {

    /**
     * Executes the Ui command and returns the result message.
     *
     * @param mainWindow {@code MainWindow} which the command should operate on.
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract void execute(MainWindow mainWindow) throws CommandException;

}

