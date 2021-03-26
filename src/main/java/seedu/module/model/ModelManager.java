package seedu.module.model;

import static java.util.Objects.requireNonNull;
import static seedu.module.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.module.commons.core.GuiSettings;
import seedu.module.commons.core.LogsCenter;
import seedu.module.model.task.Task;

/**
 * Represents the in-memory model of the module book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ModuleBook moduleBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Task> filteredTasks;

    /**
     * Initializes a ModelManager with the given moduleBook and userPrefs.
     */
    public ModelManager(ReadOnlyModuleBook moduleBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(moduleBook, userPrefs);

        logger.fine("Initializing with module book: " + moduleBook + " and user prefs " + userPrefs);

        this.moduleBook = new ModuleBook(moduleBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTasks = new FilteredList<>(this.moduleBook.getTaskList());
    }

    public ModelManager() {
        this(new ModuleBook(), new UserPrefs());
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
    public Path getModuleBookFilePath() {
        return userPrefs.getModuleBookFilePath();
    }

    @Override
    public void setModuleBookFilePath(Path moduleBookFilePath) {
        requireNonNull(moduleBookFilePath);
        userPrefs.setModuleBookFilePath(moduleBookFilePath);
    }

    //=========== ModuleBook ================================================================================

    @Override
    public void setModuleBook(ReadOnlyModuleBook moduleBook) {
        this.moduleBook.resetData(moduleBook);
    }

    @Override
    public ReadOnlyModuleBook getModuleBook() {
        return moduleBook;
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return moduleBook.hasTask(task);
    }

    @Override
    public void deleteTask(Task target) {
        moduleBook.removeTask(target);
    }

    @Override
    public void addTask(Task task) {
        moduleBook.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        moduleBook.setTask(target, editedTask);
    }

    @Override
    public void sortTasks(Comparator<Task> factor) {
        moduleBook.sortTasks(factor);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    //=========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedModuleBook}
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
        return moduleBook.equals(other.moduleBook)
                && userPrefs.equals(other.userPrefs)
                && filteredTasks.equals(other.filteredTasks);
    }

}
