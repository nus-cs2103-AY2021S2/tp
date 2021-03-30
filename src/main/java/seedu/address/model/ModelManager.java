package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.assignee.Assignee;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the HeyMatez data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final HeyMatez heyMatez;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Task> filteredTasks;

    /**
     * Initializes a ModelManager with the given HeyMatez and userPrefs.
     */
    public ModelManager(ReadOnlyHeyMatez heyMatez, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(heyMatez, userPrefs);

        logger.fine("Initializing with HEY MATEz: " + heyMatez + " and user prefs " + userPrefs);

        this.heyMatez = new HeyMatez(heyMatez);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.heyMatez.getPersonList());
        filteredTasks = new FilteredList<>(this.heyMatez.getTaskList());
    }

    public ModelManager() {
        this(new HeyMatez(), new UserPrefs());
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

    @Override
    public Path getHeyMatezFilePath() {
        return userPrefs.getHeyMatezFilePath();
    }

    @Override
    public void setHeyMatezFilePath(Path heyMatezFilePath) {
        requireNonNull(heyMatezFilePath);
        userPrefs.setHeyMatezFilePath(heyMatezFilePath);
    }

    //=========== HeyMatez ================================================================================

    @Override
    public void setHeyMatez(ReadOnlyHeyMatez heyMatez) {
        this.heyMatez.resetData(heyMatez);
    }

    @Override
    public ReadOnlyHeyMatez getHeyMatez() {
        return heyMatez;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return heyMatez.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        heyMatez.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        heyMatez.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        heyMatez.setPerson(target, editedPerson);
    }

    @Override
    public void addTask(Task task) {
        heyMatez.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        heyMatez.setTask(target, editedTask);
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return heyMatez.hasTask(task);
    }

    @Override
    public void deleteTask(Task target) {
        heyMatez.removeTask(target);
    }

    @Override
    public boolean checkAssignees(Task task) {
        Set<Assignee> assignees = task.getAssignees();

        for (Assignee assignee : assignees) {
            Name tempName = new Name(assignee.assigneeName);
            Person tempPerson = new Person(tempName);
            if (!hasPerson(tempPerson)) {
                return false;
            }
        }

        return true;
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedHeyMatez}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
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
        return heyMatez.equals(other.heyMatez)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }
}
