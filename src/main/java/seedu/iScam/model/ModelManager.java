package seedu.iScam.model;

import static java.util.Objects.requireNonNull;
import static seedu.iScam.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.iScam.commons.core.GuiSettings;
import seedu.iScam.commons.core.LogsCenter;
import seedu.iScam.model.client.Client;

/**
 * Represents the in-memory model of the iScam book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ClientBook clientBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Client> filteredClients;

    /**
     * Initializes a ModelManager with the given clientBook and userPrefs.
     */
    public ModelManager(ReadOnlyClientBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with iScam book: " + addressBook + " and user prefs " + userPrefs);

        this.clientBook = new ClientBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredClients = new FilteredList<>(this.clientBook.getClientList());
    }

    public ModelManager() {
        this(new ClientBook(), new UserPrefs());
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

    //=========== ClientBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyClientBook addressBook) {
        this.clientBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyClientBook getAddressBook() {
        return clientBook;
    }

    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return clientBook.hasClient(client);
    }

    @Override
    public void deleteClient(Client target) {
        clientBook.removeClient(target);
    }

    @Override
    public void addClient(Client client) {
        clientBook.addClient(client);
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public void setClient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);

        clientBook.setClient(target, editedClient);
    }

    //=========== Filtered Client List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Client} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Client> getFilteredClientList() {
        return filteredClients;
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClients.setPredicate(predicate);
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
        return clientBook.equals(other.clientBook)
                && userPrefs.equals(other.userPrefs)
                && filteredClients.equals(other.filteredClients);
    }

}
