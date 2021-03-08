package seedu.storemando.model;

import static java.util.Objects.requireNonNull;
import static seedu.storemando.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.storemando.commons.core.GuiSettings;
import seedu.storemando.commons.core.LogsCenter;
import seedu.storemando.model.item.Item;

/**
 * Represents the in-memory model of the storemando data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final StoreMando storeMando;
    private final UserPrefs userPrefs;
    private final FilteredList<Item> filteredItems;

    /**
     * Initializes a ModelManager with the given storeMando and userPrefs.
     */
    public ModelManager(ReadOnlyStoreMando storeMando, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(storeMando, userPrefs);

        logger.fine("Initializing with storemando: " + storeMando + " and user prefs " + userPrefs);

        this.storeMando = new StoreMando(storeMando);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredItems = new FilteredList<>(this.storeMando.getItemList());
    }

    public ModelManager() {
        this(new StoreMando(), new UserPrefs());
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
    public Path getStoreMandoFilePath() {
        return userPrefs.getStoreMandoFilePath();
    }

    @Override
    public void setStoreMandoFilePath(Path storeMandoFilePath) {
        requireNonNull(storeMandoFilePath);
        userPrefs.setStoreMandoFilePath(storeMandoFilePath);
    }

    //=========== StoreMando ================================================================================

    @Override
    public void setStoreMando(ReadOnlyStoreMando storeMando) {
        this.storeMando.resetData(storeMando);
    }

    @Override
    public ReadOnlyStoreMando getStoreMando() {
        return storeMando;
    }

    @Override
    public boolean hasItem(Item item) {
        requireNonNull(item);
        return storeMando.hasItem(item);
    }

    @Override
    public void deleteItem(Item target) {
        storeMando.removeItem(target);
    }

    @Override
    public void addItem(Item item) {
        storeMando.addItem(item);
        updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
    }

    @Override
    public void setItem(Item target, Item editedItem) {
        requireAllNonNull(target, editedItem);

        storeMando.setItem(target, editedItem);
    }

    //=========== Filtered Item List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Item} backed by the internal list of
     * {@code versionedstoreMando}
     */
    @Override
    public ObservableList<Item> getFilteredItemList() {
        return filteredItems;
    }

    @Override
    public void updateFilteredItemList(Predicate<Item> predicate) {
        requireNonNull(predicate);
        filteredItems.setPredicate(predicate);
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
        return storeMando.equals(other.storeMando)
            && userPrefs.equals(other.userPrefs)
            && filteredItems.equals(other.filteredItems);
    }

}
