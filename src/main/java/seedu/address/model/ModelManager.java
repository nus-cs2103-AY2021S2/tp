package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TaskTracker taskTracker;
    private final UserPrefs userPrefs;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Task> dailyTasks;
    private VersionedTaskTracker versionedTaskTracker;

    /**
     * Initializes a ModelManager with the given taskTracker and userPrefs.
     */

    public ModelManager(ReadOnlyTaskTracker taskTracker, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(taskTracker, userPrefs);

        logger.fine("Initializing with address book: " + taskTracker + " and user prefs " + userPrefs);

        this.taskTracker = new TaskTracker(taskTracker);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTasks = new FilteredList<>(this.taskTracker.getTaskList());
        dailyTasks = new FilteredList<>(this.taskTracker.getDailyTaskList());
        this.versionedTaskTracker = new VersionedTaskTracker();
    }

    public ModelManager() {
        this(new TaskTracker(), new UserPrefs());
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
    public Path getTaskTrackerFilePath() {
        return userPrefs.getTaskTrackerFilePath();
    }

    @Override
    public void setTaskTrackerFilePath(Path taskTrackerFilePath) {
        requireNonNull(taskTrackerFilePath);
        userPrefs.setTaskTrackerFilePath(taskTrackerFilePath);
    }

    //=========== TaskTracker ================================================================================

    @Override
    public void setTaskTracker(ReadOnlyTaskTracker taskTracker) {
        this.taskTracker.resetData(taskTracker);
    }

    @Override
    public ReadOnlyTaskTracker getTaskTracker() {
        return taskTracker;
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return taskTracker.hasTask(task);
    }

    @Override
    public boolean hasDailyTask(Task task) {
        requireNonNull(task);
        return taskTracker.hasDailyTask(task);
    }

    @Override
    public void deleteTask(Task target) {
        taskTracker.removeTask(target);
    }

    @Override
    public void deleteDailyTask(Task target) {
        if (taskTracker.hasDailyTask(target)) {
            taskTracker.removeDailyTask(target);
        }
    }

    @Override
    public void finishTask(Task target) {
        taskTracker.finishTask(target);
    }

    @Override
    public void finishDailyTask(Task target) {
        if (taskTracker.hasDailyTask(target)) {
            taskTracker.finishDailyTask(target);
        }
    }

    @Override
    public void addTask(Task task) {
        taskTracker.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void addToDailyToDoList(Task taskToAdd) {
        // handle case where task already exists
        taskTracker.addDailyTask(taskToAdd);
        updateDailyTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void removeFromDailyToDoList(Task taskToRemove) {
        // handle case where task is not found
        taskTracker.removeDailyTask(taskToRemove);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        taskTracker.setTask(target, editedTask);
    }

    @Override
    public void setDailyTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);
        taskTracker.setDailyTask(target, editedTask);
    }

    @Override
    public void sortTasks(Comparator<Task> comparator) {
        requireNonNull(comparator);
        taskTracker.sortTasks(comparator);
    }

    @Override
    public void refreshDailyTasks(Task target, Task editedTask) {
        taskTracker.refreshDailyTasks(target, editedTask);
    }

    @Override
    public void commitTaskTracker(ReadOnlyTaskTracker taskTracker) {
        requireNonNull(taskTracker);
        versionedTaskTracker.commit(new TaskTracker(taskTracker));
    }

    @Override
    public TaskTracker undoTaskTracker() {
        return versionedTaskTracker.undo();
    }

    @Override
    public TaskTracker redoTaskTracker() {
        return versionedTaskTracker.redo();
    }

    @Override
    public boolean canUndoTaskTracker() {
        return versionedTaskTracker.canUndoTaskTracker();
    }

    @Override
    public boolean canRedoTaskTracker() {
        return versionedTaskTracker.canRedoTaskTracker();
    }


    //=========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedTaskTracker}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }


    @Override
    public ObservableList<Task> getDailyTaskList() {
        return dailyTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    @Override
    public void updateDailyTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        dailyTasks.setPredicate(predicate);
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
        return taskTracker.equals(other.taskTracker)
            && userPrefs.equals(other.userPrefs)
            && filteredTasks.equals(other.filteredTasks);
    }

}
