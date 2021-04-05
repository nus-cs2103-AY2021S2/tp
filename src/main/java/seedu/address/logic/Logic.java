package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyEventBook;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventStatus;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the EventBook.
     *
     * @see seedu.address.model.Model#getEventBook()
     */
    ReadOnlyEventBook getEventBook();

    /** Returns an unmodifiable view of the filtered list of events */
    ObservableList<Event> getFilteredEventList();

    /** Returns an unmodifiable view of the filtered list of event by status */
    FilteredList<Event> getFilteredListByStatus(EventStatus status);

    /** Returns an unmodifiable view of the filtered list of events with EventStatus.TODO*/
    FilteredList<Event> getFilteredTodoList();

    /** Returns an unmodifiable view of the filtered list of events with EventStatus.BACKLOG*/
    FilteredList<Event> getFilteredBacklogList();

    /** Returns an unmodifiable view of the filtered list of events with EventStatus.In_PROGRESS*/
    FilteredList<Event> getFilteredInProgressList();

    /** Returns an unmodifiable view of the filtered list of events with EventStatus.DONE*/
    FilteredList<Event> getFilteredDoneList();

    /**
     * Returns the user prefs' event book file path.
     */
    Path getEventBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
