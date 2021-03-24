package dog.pawbook.model;

import static dog.pawbook.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import dog.pawbook.commons.core.GuiSettings;
import dog.pawbook.commons.core.LogsCenter;
import dog.pawbook.model.managedentity.Entity;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.util.Pair;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Pair<Integer, Entity>> filteredEntities;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredEntities = new FilteredList<>(this.addressBook.getEntityList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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
    public boolean hasEntity(Entity entity) {
        requireNonNull(entity);
        return addressBook.hasEntity(entity);
    }

    @Override
    public boolean hasEntity(int id) {
        return addressBook.hasEntity(id);
    }

    @Override
    public Entity getEntity(int targetID) {
        return addressBook.getEntity(targetID);
    }

    @Override
    public void deleteEntity(int targetID) {
        addressBook.removeEntity(targetID);
    }

    @Override
    public int addEntity(Entity entity) {
        return addressBook.addEntity(entity);
    }

    @Override
    public void setEntity(int targetId, Entity editedEntity) {
        requireAllNonNull(editedEntity);

        addressBook.setEntity(targetId, editedEntity);
    }

    //=========== Filtered Owner List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Entity} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Pair<Integer, Entity>> getFilteredEntityList() {
        return filteredEntities;
    }

    @Override
    public ObservableList<Pair<Integer, Entity>> getUnfilteredEntityList() {
        return addressBook.getEntityList();
    }

    @Override
    public void updateFilteredEntityList(Predicate<Pair<Integer, Entity>> predicate) {
        requireNonNull(predicate);
        filteredEntities.setPredicate(predicate);
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
                && filteredEntities.equals(other.filteredEntities);
    }
}
