package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.colabfolderhistory.SavedState;
import seedu.address.model.colabfolderhistory.exceptions.NoRedoableStateException;
import seedu.address.model.colabfolderhistory.exceptions.NoUndoableStateException;
import seedu.address.model.contact.Contact;
import seedu.address.model.project.Project;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Contact> PREDICATE_SHOW_ALL_CONTACTS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Project> PREDICATE_SHOW_ALL_PROJECTS = unused -> true;

    //=========== UserPrefs ==================================================================================

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    //=========== ColabFolder ================================================================================

    /**
     * Returns the user prefs' CoLAB folder file path.
     */
    Path getColabFolderFilePath();

    /**
     * Sets the user prefs' CoLAB folder file path.
     */
    void setColabFolderFilePath(Path colabFolderFilePath);

    /**
     * Replaces CoLAB folder data with the data in {@code colabFolder}.
     */
    void setColabFolder(ReadOnlyColabFolder colabFolder);

    /** Returns the ColabFolder */
    ReadOnlyColabFolder getColabFolder();

    /**
     * Returns true if a Contact with the same identity as {@code contact} exists in the CoLAB folder.
     */
    boolean hasContact(Contact contact);

    /**
     * Deletes the given Contact.
     * The Contact must exist in the CoLAB folder.
     */
    void deleteContact(Contact target);

    /**
     * Adds the given Contact.
     * {@code contact} must not already exist in the CoLAB folder.
     */
    void addContact(Contact contact);

    /**
     * Replaces the given Contact {@code target} with {@code editedContact}.
     * {@code target} must exist in the CoLAB folder.
     * The Contact identity of {@code editedContact} must not be the same as
     * another existing contact in the CoLAB folder.
     */
    void setContact(Contact target, Contact editedContact);

    //=========== Filtered Contact List Accessors =============================================================

    /** Returns an unmodifiable view of the filtered contact list */
    ObservableList<Contact> getFilteredContactList();

    /**
     * Updates the filter of the filtered contact list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredContactList(Predicate<Contact> predicate);

    //=========== Projects File ==============================================================================

    /**
     * Returns true if a project with the same identity as {@code project} exists in the CoLAB folder.
     */
    boolean hasProject(Project project);

    /**
     * Deletes the given project.
     * The project must exist in the CoLAB folder.
     */
    void deleteProject(Project target);

    /**
     * Adds the given project.
     * {@code project} must not already exist in the CoLAB folder.
     */
    void addProject(Project projects);

    /**
     * Replaces the given project {@code target} with {@code editedProject}.
     * {@code target} must exist in the CoLAB folder.
     * The project identity of {@code editedProject} must not be the same as
     * another existing project in the CoLAB folder.
     */
    void setProject(Project target, Project editedProject);

    //=========== Filtered Project List Accessors ===========================================================

    /** Returns an unmodifiable view of the filtered projects list */
    ObservableList<Project> getFilteredProjectList();

    /**
     * Updates the filter of the filtered projects list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredProjectList(Predicate<Project> predicate);

    //=========== Colab Folder History ======================================================================

    /**
     * Returns the previous state in {@code ColabFolderHistory}
     *
     * @throws NoUndoableStateException if previous state not found.
     */
    SavedState getUndoState() throws NoUndoableStateException;

    /**
     * Returns the next state in {@code ColabFolderHistory}
     *
     * @throws NoRedoableStateException if next state not found.
     */
    SavedState getRedoState() throws NoRedoableStateException;

    /**
     * Undo the {@code ColabFolder} to the previous state and returns the command result used to get to that state.
     *
     * @throws NoUndoableStateException if previous state not found.
     */
    CommandResult undo() throws NoUndoableStateException;

    /**
     * Redo the {@code ColabFolder} to the next state and returns the command result used to get to that state.
     *
     * @throws NoRedoableStateException if next state not found.
     */
    CommandResult redo() throws NoRedoableStateException;

    /**
     * Commits the current {@code ColabFolder} state to {@code ColabFolderHistory}.
     */
    void commitState(CommandResult commandResult);
}
