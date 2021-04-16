package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.CommandAlias;
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

    /** Returns the alias map */
    ReadOnlyUniqueAliasMap getAliasMap();

    /**
     * Adds the given command alias.
     * {@code commandAlias} must not already exist in the alias map.
     */
    void addAlias(CommandAlias commandAlias);

    /**
     * Deletes the given alias.
     * The alias must exist in the alias map.
     */
    void deleteAlias(Alias alias);

    /**
     * Returns true if an alias with the same identity as {@code alias} exists in the alias map.
     */
    boolean hasAlias(Alias alias);

    /**
     * Returns true if a command alias with the same identity as {@code commandAlias} exists in the alias map.
     */
    boolean hasCommandAlias(CommandAlias commandAlias);

    /**
     * Returns command alias of the alias in alias map.
     * If alias is not found, null is returned.
     */
    CommandAlias getCommandAlias(Alias alias);

    /**
     * Returns the number of command aliases.
     */
    int getNumOfCommandAlias();

    /**
     * Updates display filter that determines PersonCard control visibility.
     * @param displayFilterPredicate that returns true if prefix linked control should be shown.
     */
    void updateDisplayFilter(DisplayFilterPredicate displayFilterPredicate);

    /**
     * Returns display filter that determines field control visibility.
     * @return predicate that returns true if prefix linked control should be shown.
     */
    DisplayFilterPredicate getDisplayFilter();

    /**
     * Updates the select person list by appending to the existing selection.
     * Does not apply the filter on the person list.
     * @param selectedPersonList appends selected person list
     * @throws NullPointerException if {@code selectedPersonList} is null.
     */
    void updateSelectedPersonList(List<Person> selectedPersonList);

    /**
     * Clears the selected person list and lists all persons.
     */
    void clearSelectedPersonList();

    /**
     * Applies and update the filter of the filtered person list to show only selected.
     */
    void applySelectedPredicate();

    /**
     * Returns predicate that determines a Person objects selected state.
     * @return predicate that is true if Person object is selected
     */
    Predicate<Person> getSelectedPersonPredicate();

    /**
     * Returns a list of command aliases in String.
     */
    List<String> getCommandAliasesStringList();
}
