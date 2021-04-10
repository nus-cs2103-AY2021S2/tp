package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyDatesBook;
import seedu.address.model.date.ImportantDate;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    boolean isProceed(String commandText) throws ParseException;

    void setSavedState(boolean isSavedState);

    ObservableList<Lesson> getSortedLessonsForDay(String keyword);

    ObservableList<String> getLessonsForDayInString(String keyword);

    ObservableList<Lesson> getMondayLesson();
    ObservableList<Lesson> getTuesdayLesson();
    ObservableList<Lesson> getWednesdayLesson();
    ObservableList<Lesson> getThursdayLesson();
    ObservableList<Lesson> getFridayLesson();
    ObservableList<Lesson> getSaturdayLesson();
    ObservableList<Lesson> getSundayLesson();

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns the selected person
     *
     * @see seedu.address.model.Model#getSelectedPerson()
     */
    Person getSelectedPerson();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the sorted list of persons */
    ObservableList<Person> getSortedPersonList();

    /** Returns an unmodifiable view of the transformed list of persons after filtering or sorting */
    public ObservableList<Person> getTransformedPersonList();

    /** Returns an unmodifiable view of the filtered list of lessons */
    ObservableList<Lesson> getFilteredLessonList();

    /** Returns an unmodifiable view of the sorted list of lessons */
    ObservableList<Lesson> getSortedLessonList();

    /** Returns an unmodifiable view of the transformed list of lessons after filtering or sorting */
    public ObservableList<Lesson> getTransformedLessonList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    ReadOnlyDatesBook getDatesBook();

    ObservableList<ImportantDate> getFilteredImportantDatesList();

    ObservableList<ImportantDate> getSortedImportantDatesList();
    ObservableList<Lesson> getSpecificLessonList(Person selectedPerson);

    public ObservableList<ImportantDate> getTransformedImportantDatesList();

    Path getDatesBookFilePath();


    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);


}
