package seedu.storemando.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.UniqueItemList;

/**
 * Wraps all data at the storemando level
 * Duplicates are not allowed (by .isSameItem comparison)
 */
public class StoreMando implements ReadOnlyStoreMando {

    private final UniqueItemList items;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        items = new UniqueItemList();
    }

    public StoreMando() {
    }

    /**
     * Creates an StoreMando using the Items in the {@code toBeCopied}
     */
    public StoreMando(ReadOnlyStoreMando toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the item list with {@code items}.
     * {@code items} must not contain duplicate items.
     */
    public void setItems(List<Item> items) {
        this.items.setItems(items);
    }

    /**
     * Resets the existing data of this {@code StoreMando} with {@code newData}.
     */
    public void resetData(ReadOnlyStoreMando newData) {
        requireNonNull(newData);

        setItems(newData.getItemList());
    }

    //// item-level operations

    /**
     * Returns true if a item with the same identity as {@code item} exists in the storemando.
     */
    public boolean hasItem(Item item) {
        requireNonNull(item);
        return items.contains(item);
    }

    /**
     * Returns true if a item with the similar identity as {@code item} exists in the storemando.
     */
    public boolean hasSimilarItem(Item item) {
        requireNonNull(item);
        return items.containsSimilar(item);
    }


    /**
     * Adds a item to the storemando.
     * The item must not already exist in the storemando.
     */
    public void addItem(Item p) {
        items.add(p);
    }

    /**
     * Replaces the given item {@code target} in the list with {@code editedItem}.
     * {@code target} must exist in the storemando.
     * The item identity of {@code editedItem} must not be the same as another existing item in the storemando.
     */
    public void setItem(Item target, Item editedItem) {
        requireNonNull(editedItem);

        items.setItem(target, editedItem);
    }

    /**
     * Removes {@code key} from this {@code StoreMando}.
     * {@code key} must exist in the storemando.
     */
    public void removeItem(Item key) {
        items.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return items.asUnmodifiableObservableList().size() + " items";
        // TODO: refine later
    }

    @Override
    public ObservableList<Item> getItemList() {
        return items.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof StoreMando // instanceof handles nulls
            && items.equals(((StoreMando) other).items));
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }
}
