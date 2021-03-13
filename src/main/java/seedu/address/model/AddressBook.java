package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.resident.Resident;
import seedu.address.model.resident.UniqueResidentList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameResident comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueResidentList residents;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        residents = new UniqueResidentList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Residents in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the resident list with {@code residents}.
     * {@code residents} must not contain duplicate residents.
     */
    public void setResidents(List<Resident> residents) {
        this.residents.setResidents(residents);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setResidents(newData.getResidentList());
    }

    //// resident-level operations

    /**
     * Returns true if a resident with the same identity as {@code resident} exists in the address book.
     */
    public boolean hasResident(Resident resident) {
        requireNonNull(resident);
        return residents.contains(resident);
    }

    /**
     * Adds a resident to the address book.
     * The resident must not already exist in the address book.
     */
    public void addResident(Resident p) {
        residents.add(p);
    }

    /**
     * Replaces the given resident {@code target} in the list with {@code editedResident}.
     * {@code target} must exist in the address book.
     * The resident identity of {@code editedResident} must not be the same
     * as another existing resident in the address book.
     */
    public void setResident(Resident target, Resident editedResident) {
        requireNonNull(editedResident);

        residents.setResident(target, editedResident);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeResident(Resident key) {
        residents.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return residents.asUnmodifiableObservableList().size() + " residents";
        // TODO: refine later
    }

    @Override
    public ObservableList<Resident> getResidentList() {
        return residents.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && residents.equals(((AddressBook) other).residents));
    }

    @Override
    public int hashCode() {
        return residents.hashCode();
    }
}
