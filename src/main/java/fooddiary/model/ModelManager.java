package fooddiary.model;

import static fooddiary.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import fooddiary.commons.core.GuiSettings;
import fooddiary.commons.core.LogsCenter;
import fooddiary.model.entry.Entry;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;


/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FoodDiary foodDiary;
    private final UserPrefs userPrefs;
    private final FilteredList<Entry> filteredEntries;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyFoodDiary addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.foodDiary = new FoodDiary(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredEntries = new FilteredList<>(this.foodDiary.getEntryList());
    }

    public ModelManager() {
        this(new FoodDiary(), new UserPrefs());
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
    public Path getFoodDiaryFilePath() {
        return userPrefs.getFoodDiaryFilePath();
    }

    @Override
    public void setFoodDiaryFilePath(Path foodDiaryFilePath) {
        requireNonNull(foodDiaryFilePath);
        userPrefs.setFoodDiaryFilePath(foodDiaryFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setFoodDiary(ReadOnlyFoodDiary foodDiary) {
        this.foodDiary.resetData(foodDiary);
    }

    @Override
    public ReadOnlyFoodDiary getFoodDiary() {
        return foodDiary;
    }

    @Override
    public boolean hasEntry(Entry entry) {
        requireNonNull(entry);
        return foodDiary.hasEntry(entry);
    }

    @Override
    public void deleteEntry(Entry target) {
        foodDiary.removePerson(target);
    }

    @Override
    public void addEntry(Entry entry) {
        foodDiary.addEntry(entry);
        updateFilteredEntryList(PREDICATE_SHOW_ALL_ENTRIES);
    }

    @Override
    public void setEntry(Entry target, Entry editedEntry) {
        requireAllNonNull(target, editedEntry);

        foodDiary.setPerson(target, editedEntry);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Entry> getFilteredEntryList() {
        return filteredEntries;
    }

    @Override
    public void updateFilteredEntryList(Predicate<Entry> predicate) {
        requireNonNull(predicate);
        filteredEntries.setPredicate(predicate);
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
        return foodDiary.equals(other.foodDiary)
                && userPrefs.equals(other.userPrefs)
                && filteredEntries.equals(other.filteredEntries);
    }

}
