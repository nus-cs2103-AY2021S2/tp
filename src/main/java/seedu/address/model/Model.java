package seedu.address.model;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventStatus;
/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;

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

    //============ EventBook =============================================
    /**
     * Returns the user prefs' event book file path.
     */
    Path getEventBookFilePath();

    /**
     * Sets the user prefs' event book file path.
     */
    void setEventBookFilePath(Path eventBookFilePath);

    /**
     * Get an event by event identifier attribute
     */
    Optional<Event> getEventByIdentifier(int identifier);

    /**
     * Replaces event book data with the data in {@code eventBook}.
     */
    void setEventBook(ReadOnlyEventBook eventBook);

    /** Returns the EventBook */
    ReadOnlyEventBook getEventBook();

    /**
     * Returns true if an event with the same identity as {@code event} exists in the event book.
     */
    boolean hasEvent(Event event);

    /**
     * Deletes the given event.
     * The event must exist in the event book.
     */
    void deleteEvent(Event target);

    /**
     * Adds the given event.
     * {@code event} must not already exist in the event book.
     */
    void addEvent(Event event);

    /**
     * Replaces the given event {@code target} with {@code editedEvent}.
     * {@code target} must exist in the event book.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the event book.
     */
    void setEvent(Event target, Event editedEvent);

    /** Returns an unmodifiable view of the filtered event list */
    ObservableList<Event> getFilteredEventList();

    /** Returns an unmodifiable view of the filtered event list by status */
    FilteredList<Event> getFilteredListByStatus(EventStatus status);

    /** Returns an unmodifiable view of the filtered event list */
    FilteredList<Event> getFilteredBacklogList();

    /** Returns an unmodifiable view of the filtered event list */
    FilteredList<Event> getFilteredTodoList();

    /** Returns an unmodifiable view of the filtered event list */
    FilteredList<Event> getFilteredInProgressList();

    /** Returns an unmodifiable view of the filtered event list */
    FilteredList<Event> getFilteredDoneList();

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);
}
