package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.date.ImportantDate;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    Predicate<ImportantDate> PREDICATE_SHOW_ALL_IMPORTANT_DATES = unused -> true;

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

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    Path getDatesBookFilePath();

    void setDatesBookFilePath(Path datesBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();


    /**
     * Replaces selected person data with the data in {@code newPerson}.
     */
    void setSelectedPerson(Person person);

    /** Returns the selected person*/
    Person getSelectedPerson();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    void filterPerson(Predicate<Person> predicate);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /** Returns an unmodifiable view of the sorted person list */
    ObservableList<Person> getSortedPersonList();

    /**
     * Updates the comparator of the sorted person list to sort by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateSortedPersonList(Comparator<Person> comparator);

    /** Returns an unmodifiable view of the transformed person list */
    ObservableList<Person> getTransformedPersonList();

    /** Filters the transformed person list by the given {@code predicate} then sorts the
     * transformed person list by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void filterThenSortPersonList(Predicate<Person> predicate, Comparator<Person> comparator);

    void setDatesBook(ReadOnlyDatesBook datesBook);

    ReadOnlyDatesBook getDatesBook();

    boolean hasImportantDate(ImportantDate importantDate);

    void deleteImportantDate(ImportantDate target);

    void addImportantDate(ImportantDate importantDate);

    void filterImportantDates(Predicate<ImportantDate> predicate);

    ObservableList<ImportantDate> getFilteredImportantDatesList();

    void updateFilteredImportantDatesList(Predicate<ImportantDate> predicate);

    ObservableList<ImportantDate> getSortedImportantDatesList();

    void updateSortedImportantDatesList(Comparator<ImportantDate> comparator);

    ObservableList<ImportantDate> getTransformedImportantDatesList();




}
