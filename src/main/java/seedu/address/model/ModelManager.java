package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.attribute.Attribute;
import seedu.address.model.person.Person;
import seedu.address.model.shortcut.ShortcutLibrary;
import seedu.address.storage.Authentication;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private List<Person> backUpList;
    private final Authentication authentication;
    private final ObservableList<Person> modifiedList;
    private final ShortcutLibrary shortcutLibrary;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs and Authentication and shortcutLibrary.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, Authentication authentication,
                        ShortcutLibrary shortcutLibrary) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.backUpList = new ArrayList<>(this.addressBook.getPersonList());
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.authentication = authentication;
        modifiedList = this.addressBook.getModifiablePersonList();
        this.shortcutLibrary = shortcutLibrary;
    }


    /**
     * Initializes a ModelManager with the given addressBook and userPrefs and shortcutLibrary.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, ShortcutLibrary shortcutLibrary) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.backUpList = new ArrayList<>(this.addressBook.getPersonList());
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        modifiedList = this.addressBook.getModifiablePersonList();
        this.authentication = new Authentication();
        this.shortcutLibrary = shortcutLibrary;
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new Authentication(), new ShortcutLibrary());
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

    @Override
    public Path getShortcutLibraryFilePath() {
        return userPrefs.getShortcutLibraryFilePath();
    }

    @Override
    public void setShortcutLibraryFilePath(Path shortcutLibraryFilePath) {
        requireNonNull(shortcutLibraryFilePath);
        userPrefs.setShortcutLibraryFilePath(shortcutLibraryFilePath);
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
        this.backUpList = new ArrayList<>(this.addressBook.getPersonList());
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        this.backUpList = new ArrayList<>(this.addressBook.getPersonList());
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
        this.backUpList = new ArrayList<>(this.addressBook.getPersonList());
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
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updatePersonListByAttribute(Set<Attribute> attributeTypes) {
        List<Person> tempPersonsList = new ArrayList<>();
        for (int i = 0; i < filteredPersons.size(); i++) {
            Person person = filteredPersons.get(i);
            tempPersonsList.add(new Person(person, attributeTypes));
        }
        modifiedList.setAll(tempPersonsList);
    }

    @Override
    public void undoListModification() {
        modifiedList.setAll(backUpList);
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
                && shortcutLibrary.equals(other.shortcutLibrary);
    }

    //=========== Authenticator Accessors =============================================================

    public Authentication getAuthentication() {
        return this.authentication;
    }

    //=========== Sorted Person List Accessors =============================================================

    @Override
    public void updateSortedPersonList(Comparator<Person> comparator) {
        requireNonNull(comparator);
        modifiedList.sort(comparator);
    }

    //=========== Whole Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the entire person list
     */
    @Override
    public ObservableList<Person> getWholePersonList() {
        return new FilteredList<>(this.addressBook.getPersonList());
    }

    //=========== Shortcut Library ================================================================================

    @Override
    public void setShortcutLibrary(ShortcutLibrary shortcutLibrary) {
        this.shortcutLibrary.resetData(shortcutLibrary.getShortcuts());
    }

    @Override
    public ShortcutLibrary getShortcutLibrary() {
        return shortcutLibrary;
    }

    @Override
    public boolean hasShortcut(String shortcutName) {
        requireNonNull(shortcutName);
        return shortcutLibrary.hasShortcut(shortcutName);
    }

    @Override
    public void deleteShortcut(String shortcutName) {
        shortcutLibrary.removeShortcut(shortcutName);
    }

    @Override
    public void addShortcut(String shortcutName, String shortcutCommand) {
        shortcutLibrary.addShortcut(shortcutName, shortcutCommand);
    }

    @Override
    public void setShortcut(String target, String shortcutCommand) {
        requireAllNonNull(target, shortcutCommand);
        shortcutLibrary.setShortcut(target, shortcutCommand);
    }
}
