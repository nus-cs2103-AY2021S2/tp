package dog.pawbook.logic;

import java.nio.file.Path;

import dog.pawbook.commons.core.GuiSettings;
import dog.pawbook.logic.commands.CommandResult;
import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.logic.parser.exceptions.ParseException;
import dog.pawbook.model.ReadOnlyDatabase;
import dog.pawbook.model.managedentity.Entity;
import javafx.collections.ObservableList;
import javafx.util.Pair;

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
     * Returns the Database.
     *
     * @see dog.pawbook.model.Model#getDatabase()
     */
    ReadOnlyDatabase getDatabase();

    /**
     * Returns an unmodifiable view of the filtered list of entities.
     */
    ObservableList<Pair<Integer, Entity>> getFilteredEntityList();

    /**
     * Returns the user prefs' database file path.
     */
    Path getDatabaseFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
