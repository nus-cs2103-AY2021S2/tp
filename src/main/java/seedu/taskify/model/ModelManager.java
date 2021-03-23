package seedu.taskify.model;

import static java.util.Objects.requireNonNull;
import static seedu.taskify.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.taskify.commons.core.GuiSettings;
import seedu.taskify.commons.core.LogsCenter;
import seedu.taskify.model.task.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Taskify taskify;
    private final UserPrefs userPrefs;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Task> expiredFilteredTasks;
    private final FilteredList<Task> completedFilteredTasks;

    /**
     * Initializes a ModelManager with the given taskify and userPrefs.
     */
    public ModelManager(ReadOnlyTaskify taskify, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(taskify, userPrefs);

        logger.fine("Initializing with address book: " + taskify + " and user prefs " + userPrefs);

        this.taskify = new Taskify(taskify);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTasks = new FilteredList<>(this.taskify.getTaskList());
        expiredFilteredTasks = new FilteredList<>(this.taskify.getExpiredTaskList());
        completedFilteredTasks = new FilteredList<>(this.taskify.getCompletedTaskList());
    }

    public ModelManager() {
        this(new Taskify(), new UserPrefs());
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path taskifyFilePath) {
        requireNonNull(taskifyFilePath);
        userPrefs.setAddressBookFilePath(taskifyFilePath);
    }

    //=========== TaskifyParser ================================================================================

    @Override
    public void setAddressBook(ReadOnlyTaskify taskify) {
        this.taskify.resetData(taskify);
    }

    @Override
    public ReadOnlyTaskify getAddressBook() {
        return taskify;
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return taskify.hasTask(task);
    }

    @Override
    public void deleteTask(Task target) {
        taskify.removeTask(target);
    }

    @Override
    public void sortTask() {
        taskify.sortTask();
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void addTask(Task task) {
        taskify.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        taskify.setTask(target, editedTask);
    }

    //=========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public ObservableList<Task> getExpiredFilteredTaskList() {
        return expiredFilteredTasks;
    }

    @Override
    public ObservableList<Task> getCompletedFilteredTaskList() {
        return completedFilteredTasks;
    }

    @Override
    public void updateExpiredFilterTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        expiredFilteredTasks.setPredicate(predicate);
    }

    @Override
    public void updateCompletedFilterTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        completedFilteredTasks.setPredicate(predicate);
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
        return taskify.equals(other.taskify)
                       && userPrefs.equals(other.userPrefs)
                       && filteredTasks.equals(other.filteredTasks);
    }

}
