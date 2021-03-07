package dog.pawbook.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import dog.pawbook.model.owner.Owner;
import dog.pawbook.model.owner.UniqueOwnerList;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameOwner comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueOwnerList owners;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        owners = new UniqueOwnerList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Owners in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the owner list with {@code owners}.
     * {@code owners} must not contain duplicate owners.
     */
    public void setOwners(List<Owner> owners) {
        this.owners.setOwners(owners);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setOwners(newData.getOwnerList());
    }

    //// owner-level operations

    /**
     * Returns true if a owner with the same identity as {@code owner} exists in the address book.
     */
    public boolean hasOwner(Owner owner) {
        requireNonNull(owner);
        return owners.contains(owner);
    }

    /**
     * Adds a owner to the address book.
     * The owner must not already exist in the address book.
     */
    public void addOwner(Owner p) {
        owners.add(p);
    }

    /**
     * Replaces the given owner {@code target} in the list with {@code editedOwner}.
     * {@code target} must exist in the address book.
     * The owner identity of {@code editedOwner} must not be the same as another existing owner in the address book.
     */
    public void setOwner(Owner target, Owner editedOwner) {
        requireNonNull(editedOwner);

        owners.setOwner(target, editedOwner);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeOwner(Owner key) {
        owners.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return owners.asUnmodifiableObservableList().size() + " owners";
        // TODO: refine later
    }

    @Override
    public ObservableList<Owner> getOwnerList() {
        return owners.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && owners.equals(((AddressBook) other).owners));
    }

    @Override
    public int hashCode() {
        return owners.hashCode();
    }
}
