package fooddiary.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import fooddiary.model.entry.Entry;
import fooddiary.model.entry.UniqueEntryList;
import javafx.collections.ObservableList;


/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class FoodDiary implements ReadOnlyFoodDiary {

    private final UniqueEntryList entries;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        entries = new UniqueEntryList();
    }

    public FoodDiary() {
    }

    /**
     * Creates an FoodDiary using the Persons in the {@code toBeCopied}
     */
    public FoodDiary(ReadOnlyFoodDiary toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the entry list with {@code entries}.
     * {@code entries} must not contain duplicate entries.
     */
    public void setEntries(List<Entry> entries) {
        this.entries.setEntry(entries);
    }

    /**
     * Resets the existing data of this {@code FoodDiary} with {@code newData}.
     */
    public void resetData(ReadOnlyFoodDiary newData) {
        requireNonNull(newData);

        setEntries(newData.getEntryList());
    }

    //// entry-level operations

    /**
     * Returns true if a entry with the same identity as {@code entry} exists in the food diary.
     */
    public boolean hasEntry(Entry entry) {
        requireNonNull(entry);
        return entries.contains(entry);
    }

    /**
     * Adds a entry to the food diary.
     * The entry must not already exist in the food diary.
     */
    public void addEntry(Entry p) {
        entries.add(p);
    }

    /**
     * Replaces the given entry {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the food diary.
     * The entry identity of {@code editedPerson} must not be the same as another existing entry in the food diary.
     */
    public void setPerson(Entry target, Entry editedEntry) {
        requireNonNull(editedEntry);

        entries.setEntry(target, editedEntry);
    }

    /**
     * Removes {@code key} from this {@code FoodDiary}.
     * {@code key} must exist in the food diary.
     */
    public void removePerson(Entry key) {
        entries.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return entries.asUnmodifiableObservableList().size() + " entries";
        // TODO: refine later
    }

    @Override
    public ObservableList<Entry> getEntryList() {
        return entries.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FoodDiary // instanceof handles nulls
                && entries.equals(((FoodDiary) other).entries));
    }

    @Override
    public int hashCode() {
        return entries.hashCode();
    }
}
