package dog.pawbook.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.owner.UniqueEntityList;
import javafx.collections.ObservableList;
import javafx.util.Pair;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameOwner comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueEntityList entities;

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
     */
    public void setEntities(List<Pair<Integer, Entity>> entities) {
        this.entities.setEntities(entities);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setEntities(newData.getEntityList());
    }

    // entity-level operations

    /**
     * Returns true if a entity {@code entity} exists in the address book.
     */
    public boolean hasEntity(Entity entity) {
        requireNonNull(entity);
        return entities.contains(entity);
    }

    /**
     * Returns true if an entity with the same identity as {@code entity} exists in the address book.
     */
    public boolean hasEntity(int id) {
        return entities.contains(id);
    }

    /**
     * Adds a entity to the address book.
     * The entity must not already exist in the address book.
     */
    public void addEntity(Entity p) {
        entities.add(p);
    }

    /**
     * Adds a entity with its ID to the address book.
     * The entity must not already exist in the address book.
     */
    public void addEntityWithId(Entity entity, int id) {
        entities.add(entity, id);
    }

    /**
     * Replaces the given entity {@code targetId} in the list with {@code editedOwner}.
     * {@code targetId} must exist in the address book.
     * The entity identity of {@code editedOwner} must not be the same as another existing entity in the address book.
     */
    public void setEntity(int targetId, Entity editedEntity) {
        requireNonNull(editedEntity);

        entities.setEntity(targetId, editedEntity);
    }

    /**
     * Removes {@code id} from this {@code AddressBook}.
     * {@code id} must exist in the address book.
     */
    public void removeEntity(int id) {
        entities.remove(id);
    }

    //// util methods

    @Override
    public String toString() {
        return entities.asUnmodifiableObservableList().size() + " owners";
        // TODO: refine later
    }

    @Override
    public ObservableList<Pair<Integer, Entity>> getEntityList() {
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
