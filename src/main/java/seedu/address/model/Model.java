package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
     * Returns true if a person with the same identity as {@code person} exists in the CoLAB folder.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the CoLAB folder.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the CoLAB folder.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the CoLAB folder.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the CoLAB folder.
     */
    void setPerson(Person target, Person editedPerson);

    //=========== Filtered Person List Accessors =============================================================

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

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
}
