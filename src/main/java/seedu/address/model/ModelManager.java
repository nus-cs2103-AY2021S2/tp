package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.CommandAlias;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final UniqueAliasMap aliasMap;
    private final SortedList<Person> sortedFilteredPersons;
    private DisplayFilterPredicate displayFilterPredicate;
    private List<Person> selectedPersonList;

    /**
     * Initializes a ModelManager with the given addressBook, userPrefs, aliasMap.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, ReadOnlyUniqueAliasMap aliasMap) {
        super();
        requireAllNonNull(addressBook, userPrefs, aliasMap);

        logger.fine("Initializing with address book: " + addressBook + ", command aliases: " + aliasMap
                + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        sortedFilteredPersons = new SortedList<>(filteredPersons);
        this.aliasMap = new UniqueAliasMap(aliasMap);
        displayFilterPredicate = new DisplayFilterPredicate();
        selectedPersonList = new ArrayList<>();
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new UniqueAliasMap());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        selectedPersonList.remove(target);
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        if (selectedPersonList.remove(target)) {
            selectedPersonList.add(editedPerson);
        }
        addressBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> displayFilter) {
        requireNonNull(displayFilter);
        filteredPersons.setPredicate(displayFilter);
    }

    //=========== Display Filter Accessors ===================================================================

    @Override
    public void updateDisplayFilter(DisplayFilterPredicate displayFilterPredicate) {
        this.displayFilterPredicate = displayFilterPredicate;
    }

    @Override
    public DisplayFilterPredicate getDisplayFilter() {
        return displayFilterPredicate;
    }

    @Override
    public void updateSelectedPersonList(List<Person> selectedPersonList) {
        requireNonNull(selectedPersonList);
        this.selectedPersonList.addAll(selectedPersonList);
    }

    @Override
    public void clearSelectedPersonList() {
        this.selectedPersonList = new ArrayList<>();
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void applySelectedPredicate() {
        updateFilteredPersonList((person) -> selectedPersonList.contains(person));
    }

    @Override
    public Predicate<Person> getSelectedPersonPredicate() {
        return (person) -> selectedPersonList.contains(person);
    }

    @Override
    public ReadOnlyUniqueAliasMap getAliasMap() {
        return aliasMap;
    }

    @Override
    public void addAlias(CommandAlias commandAlias) {
        aliasMap.addAlias(commandAlias);
    }

    @Override
    public void deleteAlias(Alias alias) {
        aliasMap.removeAlias(alias);
    }

    @Override
    public boolean hasAlias(Alias alias) {
        requireNonNull(alias);
        return aliasMap.hasAlias(alias);
    }

    @Override
    public boolean hasCommandAlias(CommandAlias commandAlias) {
        requireNonNull(commandAlias);
        return aliasMap.hasCommandAlias(commandAlias);
    }

    @Override
    public CommandAlias getCommandAlias(Alias alias) {
        requireNonNull(alias);
        return aliasMap.getCommandAlias(alias);
    }

    @Override
    public int getNumOfCommandAlias() {
        return aliasMap.getNumOfAlias();
    }

    @Override
    public List<String> getCommandAliasesStringList() {
        return aliasMap.getCommandAliasesStringList();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && aliasMap.equals(other.aliasMap)
                && selectedPersonList.containsAll(other.selectedPersonList)
                && other.selectedPersonList.containsAll(selectedPersonList);
    }

}
