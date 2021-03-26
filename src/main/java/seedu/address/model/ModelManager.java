package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;

/**
 * Represents the in-memory model of the CoLAB folder data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ColabFolder colabFolder;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Project> filteredProjects;

    /**
     * Initializes a ModelManager with the given colabFolder and userPrefs.
     */
    public ModelManager(ReadOnlyColabFolder colabFolder, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(colabFolder, userPrefs);

        logger.fine("Initializing with CoLAB folder: " + colabFolder + " and user prefs " + userPrefs);

        this.colabFolder = new ColabFolder(colabFolder);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.colabFolder.getPersonList());
        filteredProjects = new FilteredList<>(this.colabFolder.getProjectsList());
    }

    public ModelManager() {
        this(new ColabFolder(), new UserPrefs());
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return colabFolder.equals(other.colabFolder)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredProjects.equals(other.filteredProjects);
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);

        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);

        userPrefs.setGuiSettings(guiSettings);
    }

    //=========== ColabFolder ================================================================================

    @Override
    public Path getColabFolderFilePath() {
        return userPrefs.getColabFolderFilePath();
    }

    @Override
    public void setColabFolderFilePath(Path colabFolderFilePath) {
        requireNonNull(colabFolderFilePath);

        userPrefs.setColabFolderFilePath(colabFolderFilePath);
    }

    @Override
    public void setColabFolder(ReadOnlyColabFolder colabFolder) {
        this.colabFolder.resetData(colabFolder);
    }

    @Override
    public ReadOnlyColabFolder getColabFolder() {
        return colabFolder;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);

        return colabFolder.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        requireNonNull(target);

        colabFolder.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        colabFolder.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        colabFolder.setPerson(target, editedPerson);
    }

    @Override
    public boolean hasProject(Project project) {
        requireNonNull(project);

        return colabFolder.hasProject(project);
    }

    @Override
    public void deleteProject(Project target) {
        requireNonNull(target);

        colabFolder.removeProject(target);
    }

    @Override
    public void addProject(Project project) {
        requireNonNull(project);

        colabFolder.addProject(project);
        updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
    }

    @Override
    public void setProject(Project target, Project editedProject) {
        requireAllNonNull(target, editedProject);

        colabFolder.setProject(target, editedProject);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedColabFolder}.
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);

        filteredPersons.setPredicate(predicate);
    }

    //=========== Filtered Projects List Accessors ==========================================================

    /**
     * Returns an unmodifiable view of the list of {@code Project} backed by the internal list of
     * {@code versionedColabFolder}.
     */
    @Override
    public ObservableList<Project> getFilteredProjectList() {
        return filteredProjects;
    }

    @Override
    public void updateFilteredProjectList(Predicate<Project> predicate) {
        requireNonNull(predicate);

        filteredProjects.setPredicate(predicate);
    }
}
