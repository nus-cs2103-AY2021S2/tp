package seedu.address.logic;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyColabFolder;
import seedu.address.model.contact.Contact;
import seedu.address.model.project.Project;

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
     * Returns the ColabFolder.
     *
     * @see Model#getColabFolder()
     */
    ReadOnlyColabFolder getColabFolder();

    /** Returns an unmodifiable view of the filtered list of contacts */
    ObservableList<Contact> getFilteredContactList();

    /**
     * Returns the user prefs' CoLAB folder file path.
     */
    Path getColabFolderFilePath();


    /** Returns an unmodifiable view of the filtered list of projects */
    ObservableList<Project> getFilteredProjectsList();

    /**
     * Updates the filtered contact list with the new {@code Predicate<Contact>}.
     *
     * @param predicate The {@code Predicate<Contact>} to update the filtered contact list with.
     */
    void updateFilteredContactList(Predicate<Contact> predicate);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Commits the current {@code ColabFolder} state to {@code ColabFolderHistory}.
     */
    void commitState(CommandResult commandResult);
}
