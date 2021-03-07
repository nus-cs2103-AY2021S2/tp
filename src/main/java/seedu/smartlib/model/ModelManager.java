package seedu.smartlib.model;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.smartlib.commons.core.GuiSettings;
import seedu.smartlib.commons.core.LogsCenter;
import seedu.smartlib.model.reader.Reader;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final SmartLib smartLib;
    private final UserPrefs userPrefs;
    private final FilteredList<Reader> filteredReaders;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlySmartLib addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.smartLib = new SmartLib(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredReaders = new FilteredList<>(this.smartLib.getPersonList());
    }

    public ModelManager() {
        this(new SmartLib(), new UserPrefs());
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
    public void setSmartLib(ReadOnlySmartLib smartLib) {
        this.smartLib.resetData(smartLib);
    }

    @Override
    public ReadOnlySmartLib getSmartLib() {
        return smartLib;
    }

    @Override
    public boolean hasReader(Reader reader) {
        requireNonNull(reader);
        return smartLib.hasReader(reader);
    }

    @Override
    public void deleteReader(Reader target) {
        smartLib.removeReader(target);
    }

    @Override
    public void addReader(Reader reader) {
        smartLib.addReader(reader);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Reader target, Reader editedReader) {
        requireAllNonNull(target, editedReader);

        smartLib.setPerson(target, editedReader);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Reader> getFilteredReaderList() {
        return filteredReaders;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Reader> predicate) {
        requireNonNull(predicate);
        filteredReaders.setPredicate(predicate);
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
        return smartLib.equals(other.smartLib)
                && userPrefs.equals(other.userPrefs)
                && filteredReaders.equals(other.filteredReaders);
    }

}
