package seedu.student.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.student.commons.core.GuiSettings;
import seedu.student.logic.commands.CommandResult;
import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.logic.parser.exceptions.ParseException;
import seedu.student.model.Model;
import seedu.student.model.ReadOnlyStudentBook;
import seedu.student.model.appointment.SameDateAppointmentList;
import seedu.student.model.student.Student;

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
     * Returns the AddressBook.
     *
     * @see Model#getStudentBook()
     */
    ReadOnlyStudentBook getStudentBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Student> getFilteredStudentList();

    ObservableList<SameDateAppointmentList> getFilteredAppointmentList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getStudentBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
