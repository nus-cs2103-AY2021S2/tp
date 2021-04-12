package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.date.ImportantDate;
import seedu.address.model.date.UniqueDateList;

public class DatesBook implements ReadOnlyDatesBook {

    private final UniqueDateList importantDates;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        importantDates = new UniqueDateList();
    }

    public DatesBook() {}

    /**
     * Creates a DatesBook using the ImportantDates in the {@code toBeCopied}
     */
    public DatesBook(ReadOnlyDatesBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the important dates list with {@code importantDates}.
     * {@code importantDates} must not contain duplicate important dates.
     */
    public void setImportantDates(List<ImportantDate> importantDates) {
        this.importantDates.setImportantDates(importantDates);
    }

    /**
     * Resets the existing data of this {@code DatesBook} with {@code newData}.
     */
    public void resetData(ReadOnlyDatesBook newData) {
        requireNonNull(newData);

        setImportantDates(newData.getImportantDatesList());
    }

    //// importantDates-level operations

    /**
     * Returns true if an important date with the same description as {@code importantDate} exists in the
     * dates book.
     */
    public boolean hasImportantDate(ImportantDate importantDate) {
        requireNonNull(importantDate);
        return importantDates.contains(importantDate);
    }

    /**
     * Adds an important date to the dates book.
     * The important date must not already exist in the dates book.
     */
    public void addImportantDate(ImportantDate date) {
        importantDates.add(date);
    }

    /**
     * Removes {@code key} from this {@code DatesBook}.
     * {@code key} must exist in the dates book.
     */
    public void removeImportantDate(ImportantDate key) {
        importantDates.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return importantDates.asUnmodifiableObservableList().size() + " important dates";
    }

    @Override
    public ObservableList<ImportantDate> getImportantDatesList() {
        return importantDates.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DatesBook // instanceof handles nulls
                && importantDates.equals(((DatesBook) other).importantDates));
    }

    @Override
    public int hashCode() {
        return importantDates.hashCode();
    }
}
