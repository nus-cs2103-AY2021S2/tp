package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.plan.Plan;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Plan> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
     * Returns true if a plan with the same identity as {@code plan} exists in the address book.
     */
    boolean hasPerson(Plan plan);

    /**
     * Deletes the given plan.
     * The plan must exist in the address book.
     */
    void deletePerson(Plan target);

    /**
     * Adds the given plan.
     * {@code plan} must not already exist in the address book.
     */
    void addPerson(Plan plan);

    /**
     * Replaces the given plan {@code target} with {@code editedPlan}.
     * {@code target} must exist in the address book.
     * The plan identity of {@code editedPlan} must not be the same as another existing plan in the address book.
     */
    void setPerson(Plan target, Plan editedPlan);

    /** Returns an unmodifiable view of the filtered plan list */
    ObservableList<Plan> getFilteredPersonList();

    /**
     * Updates the filter of the filtered plan list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Plan> predicate);
}
