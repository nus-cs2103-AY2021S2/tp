package seedu.storemando.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.storemando.commons.core.GuiSettings;
import seedu.storemando.model.item.Item;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Item> PREDICATE_SHOW_ALL_ITEMS = unused -> true;

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
     * Returns the user prefs' storemando file path.
     */
    Path getStoreMandoFilePath();

    /**
     * Sets the user prefs' storemando file path.
     */
    void setStoreMandoFilePath(Path storeMandoFilePath);

    /**
     * Replaces storemando data with the data in {@code storeMando}.
     */
    void setStoreMando(ReadOnlyStoreMando storeMando);

    /**
     * Returns the StoreMando
     */
    ReadOnlyStoreMando getStoreMando();

    /**
     * Returns true if a item with the same identity as {@code item} exists in the storemando.
     */
    boolean hasItem(Item item);

    /**
     * Deletes the given item.
     * The item must exist in the storemando.
     */
    void deleteItem(Item target);

    /**
     * Adds the given item.
     * {@code item} must not already exist in the storemando.
     */
    void addItem(Item item);

    /**
     * Replaces the given item {@code target} with {@code editedItem}.
     * {@code target} must exist in the storemando.
     * The item identity of {@code editedItem} must not be the same as another existing item in the storemando.
     */
    void setItem(Item target, Item editedItem);

    /**
     * Replaces every item in StoreMando's uniqueItemList with items in {@code itemList}.
     */
    void setItems(List<Item> itemList);

    /**
     * Returns an unmodifiable view of the filtered item list
     */
    ObservableList<Item> getFilteredItemList();

    /**
     * Updates the filter of the filtered item list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredItemList(Predicate<Item> predicate);

    /**
     * Returns an unmodifiable view of the sorted item list
     */
    ObservableList<Item> getSortedItemList();

    /**
     * Sorts the list
     *
     * @param comparator
     */
    void updateSortedItemList(Comparator<Item> comparator);

    /**
     * Returns a copy of the current predicate the filtered list has gone through.
     */
    Predicate<Item> getCurrentPredicate();

    /**
     * Updates the current predicate stored in the model.
     * @param other another predicate that is being used newly.
     */
    void updateCurrentPredicate(Predicate<Item> other);
}

