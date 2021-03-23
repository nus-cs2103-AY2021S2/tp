package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the planner data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Planner planner;
    private final UserPrefs userPrefs;
    private final FilteredList<Task> filteredTasks;
    private final SortedList<Task> sortedTask;

    /**
     * Initializes a ModelManager with the given planner and userPrefs.
     */
    public ModelManager(ReadOnlyPlanner planner, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(planner, userPrefs);

        logger.fine("Initializing with planner: " + planner + " and user prefs " + userPrefs);

        this.planner = new Planner(planner);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTasks = new FilteredList<>(this.planner.getTaskList());
        sortedTask = new SortedList<>(this.planner.getTaskList());
    }

    public ModelManager() {
        this(new Planner(), new UserPrefs());
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
    public Path getPlannerFilePath() {
        return userPrefs.getPlannerFilePath();
    }

    @Override
    public void setPlannerFilePath(Path plannerFilePath) {
        requireNonNull(plannerFilePath);
        userPrefs.setPlannerFilePath(plannerFilePath);
    }

    //=========== Planner ================================================================================

    @Override
    public void setPlanner(ReadOnlyPlanner planner) {
        this.planner.resetData(planner);
    }

    @Override
    public ReadOnlyPlanner getPlanner() {
        return planner;
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return planner.hasTask(task);
    }

    @Override
    public boolean dateOver(Task task) {
        requireNonNull(task);
        return planner.dateOver(task);
    }

    @Override
    public void deleteTask(Task target) {
        planner.removeTask(target);
    }

    @Override
    public void addTask(Task task) {
        planner.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        planner.setTask(target, editedTask);
    }

    @Override
    public String countdownTask(Task task) {
        return planner.countdown(task);
    }

    //=========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedPlanner}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    @Override
    public void updateSortedTaskList(Comparator<Task> comparator) {
        requireAllNonNull(comparator);
        sortedTask.setComparator(comparator);
        planner.setTask(sortedTask);
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
        return planner.equals(other.planner)
                && userPrefs.equals(other.userPrefs)
                && filteredTasks.equals(other.filteredTasks);
    }

}
