package seedu.heymatez.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.heymatez.commons.core.GuiSettings;
import seedu.heymatez.logic.commands.CommandResult;
import seedu.heymatez.logic.commands.exceptions.CommandException;
import seedu.heymatez.logic.parser.exceptions.ParseException;
import seedu.heymatez.model.Model;
import seedu.heymatez.model.ReadOnlyHeyMatez;
import seedu.heymatez.model.person.Person;
import seedu.heymatez.model.task.Task;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the HeyMatez.
     *
     * @see Model#getHeyMatez()
     */
    ReadOnlyHeyMatez getHeyMatez();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of tasks */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Returns the user prefs' hey matez file path.
     */
    Path getHeyMatezFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
