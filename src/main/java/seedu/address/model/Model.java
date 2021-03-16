package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.module.TitleContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

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

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if a module with the same title, exams, and assignments as {@code module}
     * exists in the address book.
     */
    boolean hasModule(Module module);

    /**
     * Adds the given module.
     * {@code module} must not already exists in RemindMe.
     */
    void addModule(Module module);

    /**
     * Deletes the given module.
     * The Module must exist in the RemindMe.
     */
    void deleteModule(Module target);

    /**
     * Returns true if an assignment that has the same description and deadline
     * as {@code assignment} exists in the same module.
     */
    boolean hasAssignment(Module module, Assignment assignment);

    /**
     * Adds the given assignment.
     * {@code assignment} must not already exist in the module it is to be added to.
     */
    void addAssignment(Module module, Assignment assignment);

    /**
     * Returns true if an exam with the same date and time as {@code module} exists in the
     * address book.
     */
    boolean hasExam(Module module, Exam exam);

    /**
     * Adds the given exam.
     * {@code exam} must not already exist in the module it is to be added to.
     */
    void addExam(Module module, Exam exam);

    /**
     * Replaces module planner data with the data in {@code modulePlanner}.
     */
    void setModulePlanner(ModulePlanner modulePlanner);

    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(TitleContainsKeywordsPredicate predicate);

    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getFilteredModuleList();
}
