package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
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
    private final UniqueAliasMap aliases;
    private final SortedList<Person> sortedFilteredPersons;
    private DisplayFilterPredicate displayFilterPredicate;

    /**
     * Initializes a ModelManager with the given addressBook, userPrefs, aliases.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, ReadOnlyUniqueAliasMap aliases) {
        super();
        requireAllNonNull(addressBook, userPrefs, aliases);

        logger.fine("Initializing with address book: " + addressBook + ", aliases: " + aliases
                + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        sortedFilteredPersons = new SortedList<>(filteredPersons);
        this.aliases = new UniqueAliasMap(aliases);
        displayFilterPredicate = new DisplayFilterPredicate();
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

    //=========== Sorted Filtered Person List Accessors ======================================================

    @Override
    public ObservableList<Person> getSortedFilteredPersonList() {
        return sortedFilteredPersons;
    }

    @Override
    public void updateSortedFilteredPersonList(Comparator<Person> comparator) {
        requireNonNull(comparator);
        sortedFilteredPersons.setComparator(comparator);
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
    public ReadOnlyUniqueAliasMap getAliases() {
        return aliases;
    }

    @Override
    public ObservableList<String> getObservableStringAliases() {
        return aliases.getObservableStringAliases();
    }

    @Override
    public void addAlias(CommandAlias commandAlias) {
        aliases.addAlias(commandAlias);
    }

    @Override
    public void deleteAlias(Alias alias) {
        aliases.removeAlias(alias);
    }

    @Override
    public boolean hasAlias(Alias alias) {
        requireNonNull(alias);
        return aliases.hasAlias(alias);
    }

    @Override
    public boolean hasCommandAlias(CommandAlias commandAlias) {
        requireNonNull(commandAlias);
        return aliases.hasCommandAlias(commandAlias);
    }

    @Override
    public CommandAlias getCommandAlias(Alias alias) {
        requireNonNull(alias);
        return aliases.getCommandAlias(alias);
    }

    @Override
    public int getNumOfAlias() {
        return aliases.getNumOfAlias();
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
                && aliases.equals(other.aliases);
    }

}
