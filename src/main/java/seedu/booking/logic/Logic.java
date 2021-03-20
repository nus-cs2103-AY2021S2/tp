package seedu.booking.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.booking.commons.core.GuiSettings;
import seedu.booking.logic.commands.CommandResult;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.ReadOnlyBookingSystem;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.person.Person;
import seedu.booking.model.venue.Venue;

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
     * Returns the c.
     *
     * @see seedu.booking.model.Model#getBookingSystem()
     */
    ReadOnlyBookingSystem getBookingSystem();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the upcoming list of bookings */
    ObservableList<Booking> getUpcomingBookingList();

    /** Returns an unmodifiable view of the filtered list of bookings */
    ObservableList<Booking> getFilteredBookingList();

    /** Returns an unmodifiable view of the filtered list of venue */
    ObservableList<Venue> getFilteredVenueList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getBookingSystemFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
