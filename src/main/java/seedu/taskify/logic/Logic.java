package seedu.taskify.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.taskify.commons.core.GuiSettings;
import seedu.taskify.logic.commands.CommandResult;
import seedu.taskify.logic.commands.exceptions.CommandException;
import seedu.taskify.logic.parser.exceptions.ParseException;
import seedu.taskify.model.ReadOnlyTaskify;
import seedu.taskify.model.task.Task;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the TaskifyParser.
     *
     * @see seedu.taskify.model.Model#getAddressBook()
     */
    ReadOnlyTaskify getAddressBook();

    /**
     * Returns an unmodifiable view of the filtered list of tasks
     */
    ObservableList<Task> getFilteredTaskList();

    ObservableList<Task> getExpiredFilteredTaskList();

    ObservableList<Task> getCompletedFilteredTaskList();

    ObservableList<Task> getTodaysFilteredTaskList();

    ObservableList<Task> getUncompletedFilteredTaskList();


    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
