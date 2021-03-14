package dog.pawbook.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.owner.UniqueEntityList;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameOwner comparison)
 */
public class AddressBook<T extends Entity> implements ReadOnlyAddressBook<T> {

    private final UniqueEntityList<T> entities;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        entities = new UniqueEntityList();
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
     * @param entities
     */
    public void setEntities(List<T> entities) {
        this.entities.setEntities(entities);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setEntities(newData.getEntityList());
    }

    //// owner-level operations

    /**
     * Returns true if a owner with the same identity as {@code owner} exists in the address book.
     * @param entity
     */
    public boolean hasEntity(T entity) {
        requireNonNull(entity);
        return entities.contains(entity);
    }

    /**
     * Adds a owner to the address book.
     * The owner must not already exist in the address book.
     */
    public void addEntity(T p) {
        entities.add(p);
    }

    /**
     * Replaces the given entity {@code target} in the list with {@code editedOwner}.
     * {@code target} must exist in the address book.
     * The entity identity of {@code editedOwner} must not be the same as another existing entity in the address book.
     */
    public void setEntity(T target, T editedEntity) {
        requireNonNull(editedEntity);

        entities.setEntity(target, editedEntity);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeEntity(T key) {
        entities.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return entities.asUnmodifiableObservableList().size() + " owners";
        // TODO: refine later
    }

    @Override
    public ObservableList<T> getEntityList() {
        return entities.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && entities.equals(((AddressBook) other).entities));
    }

    @Override
    public int hashCode() {
        return entities.hashCode();
    }
}
