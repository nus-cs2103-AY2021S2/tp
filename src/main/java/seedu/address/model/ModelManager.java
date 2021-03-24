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
import seedu.address.model.event.Event;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the Sochedule data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final UserPrefs userPrefs;
    private final Sochedule sochedule;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Event> filteredEvents;

    /**
     * Initializes a ModelManager with the given Sochedule and userPrefs.
     */
    public ModelManager(ReadOnlySochedule sochedule, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(sochedule, userPrefs);

        logger.fine("Initializing with SOChedule: " + sochedule + " and user prefs " + userPrefs);

        this.sochedule = new Sochedule(sochedule);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTasks = new FilteredList<>(this.sochedule.getTaskList());
        filteredEvents = new FilteredList<>(this.sochedule.getEventList());
    }

    public ModelManager() {
        this(new Sochedule(), new UserPrefs());
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
        // null check is temporary during transition to new data
        ModelManager other = (ModelManager) obj;
        return (sochedule == null || sochedule.equals(other.sochedule))
                && (userPrefs.equals(other.userPrefs))
                && (filteredTasks == null || filteredTasks.equals(other.filteredTasks))
                && (filteredEvents == null || filteredEvents.equals(other.filteredEvents));
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

    //=========== SOChedule ==================================================================================
    @Override
    public void setSochedule(ReadOnlySochedule sochedule) {
        this.sochedule.resetTaskData(sochedule);
        this.sochedule.resetEventData(sochedule);
    }

    @Override
    public ReadOnlySochedule getSochedule() {
        return sochedule;
    }

    @Override
    public Path getSocheduleFilePath() {
        return userPrefs.getSocheduleFilePath();
    }

    @Override
    public void setSocheduleFilePath(Path socheduleFilePath) {
        requireAllNonNull(socheduleFilePath);
        userPrefs.setSocheduleFilePath(socheduleFilePath);
    }

    //=========== task ==================================================================================

    @Override
    public boolean hasTask(Task task) {
        requireAllNonNull(task);
        return sochedule.hasTask(task);
    }

    @Override
    public void deleteTask(Task target) {
        sochedule.removeTask(target);
    }

    @Override
    public void addTask(Task task) {
        sochedule.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);
        sochedule.setTask(target, editedTask);
    }

    @Override
    public void doneTask(Task task) {
        requireAllNonNull(task);
        sochedule.doneTask(task);
    }

    @Override
    public void sortTasks(String comparingVar) {
        requireNonNull(comparingVar);
        sochedule.sortTasks(comparingVar);
    }

    @Override
    public void clearExpiredTasks() {
        sochedule.clearExpiredTasks();
    }

    @Override
    public void clearCompletedTasks() {
        sochedule.clearCompletedTasks();
    }

    /**
     * Returns the number of completed tasks.
     */
    @Override
    public int getNumCompletedTask() {
        return sochedule.getNumCompletedTask();
    }

    /**
     * Returns the number of overdue tasks.
     */
    public int getNumOverdueTask() {
        return sochedule.getNumOverdueTask();
    }

    /**
     * Returns the number of incompleted tasks before deadline.
     */
    public int getNumIncompleteTask() {
        return sochedule.getNumIncompleteTask();
    }

    /**
     * Returns an unmodifiable view of the list of {@code Task}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireAllNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    //=========== event ==================================================================================

    @Override
    public boolean hasEvent(Event event) {
        requireAllNonNull(event);
        return sochedule.hasEvent(event);
    }

    @Override
    public void deleteEvent(Event target) {
        sochedule.removeEvent(target);
    }

    @Override
    public void addEvent(Event event) {
        sochedule.addEvent(event);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);
        sochedule.setEvent(target, editedEvent);
    }

    @Override
    public void sortEvents(String comparingVar) {
        requireNonNull(comparingVar);
        sochedule.sortEvents(comparingVar);
    }

    @Override
    public int getNumIncomingEvents() {
        return sochedule.getNumIncomingEvents();
    }

    public void clearExpiredEvents() {
        sochedule.clearExpiredEvents();
    }

    /**
     * Returns an unmodifiable view of the list of {@code Event}
     */
    @Override
    public ObservableList<Event> getFilteredEventList() {
        return filteredEvents;
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireAllNonNull(predicate);
        filteredEvents.setPredicate(predicate);
    }
}
