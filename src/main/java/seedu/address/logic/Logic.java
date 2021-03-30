package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ObservableCalendarDate;
import seedu.address.model.ReadOnlyPlanner;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

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
     * Returns the Planner.
     *
     * @see seedu.address.model.Model#getPlanner()
     */
    ReadOnlyPlanner getPlanner();

    /** Returns an unmodifiable view of the filtered list of tasks */
    ObservableList<Task> getFilteredTaskList();

    /** Returns an unmodifiable view of the sorted list of tags */
    ObservableList<Tag> getSortedTagList();

    /** Returns an unmodifiable view of the day requested to be viewed on the calendar */
    ObservableCalendarDate getCalendarDate();

    /**
     * Returns the user prefs' planner file path.
     */
    Path getPlannerFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
