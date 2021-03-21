package dog.pawbook.model.managedentity;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

import java.util.Iterator;
import java.util.List;

import dog.pawbook.model.managedentity.exceptions.DuplicateEntityException;
import dog.pawbook.model.managedentity.exceptions.EntityNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

/**
 * A list of entities that enforces uniqueness between its elements and does not allow nulls.
 *
 * Supports a minimal set of list operations.
 */

public class UniqueEntityList implements Iterable<Pair<Integer, Entity>> {

    private int newId = 1; // id is never 0
    private final ObservableList<Pair<Integer, Entity>> internalList = FXCollections.observableArrayList();
    private final ObservableList<Pair<Integer, Entity>> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Checks if list contains an entity of a particular id.
     *
     * @param id entity id.
     * @return boolean of whether entity exists.
     */
    public boolean contains(int id) {
        return internalList.stream().anyMatch(p -> p.getKey() == id);
    }

    /**
     * Checks if list contains a particular entity
     *
     * @param toCheck entity
     * @return boolean of whether entity exists.
     */
    public boolean contains(Entity toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(p -> toCheck.equals(p.getValue()));
    }

    /**
     * Retrieve the index of an entity stored in the internal list.
     * @return the index if found, -1 otherwise.
     */
    private int getIndexOf(int id) {
        List<Pair<Integer, Entity>> targets = internalList.stream()
                .filter(p -> p.getKey() == id)
                .collect(toList());

        if (targets.size() == 0) {
            return -1;
        }

        // there should only be one match
        assert targets.size() == 1;

        return internalList.indexOf(targets.get(0));
    }

    private int genID() {
        while (contains(newId)) {
            ++newId;
        }

        return newId;
    }

    /**
     * Adds an entity to the list.
     * The entity must not already exist in the list.
     */
    public int add(Entity toAdd) {
        requireNonNull(toAdd);

        if (contains(toAdd)) {
            throw new DuplicateEntityException();
        }
        int idNumber = genID();
        internalList.add(new Pair<>(idNumber, toAdd));
        return idNumber;
    }

    /**
     * Adds an entity to the list with a given id.
     * Both the entity and id should not already exist in the list.
     */
    public void add(Entity toAdd, int id) {
        requireNonNull(toAdd);

        if (contains(toAdd) || contains(id)) {
            throw new DuplicateEntityException();
        }
        internalList.add(new Pair<>(id, toAdd));
    }

    /**
     * Replaces the entity {@code target} in the list with {@code editedEntity}.
     * {@code target} must exist in the list.
     * The entity identity of {@code editedEntity} must not be the same as another existing entity in the list.
     */
    public void setEntity(int targetID, Entity editedEntity) {
        requireNonNull(editedEntity);

        int index = getIndexOf(targetID);
        if (index == -1) {
            throw new EntityNotFoundException();
        }

        Entity originalEntity = internalList.get(index).getValue();

        if (!originalEntity.equals(editedEntity) && contains(editedEntity)) {
            throw new DuplicateEntityException();
        }

        assert editedEntity.getClass() == originalEntity.getClass() : "The entity should not change for the same ID!";

        internalList.set(index, new Pair<>(targetID, editedEntity));
    }

    /**
     * Removes the entity with the given ID.
     */
    public void remove(int toRemoveId) {
        int index = getIndexOf(toRemoveId);
        if (index == -1) {
            throw new EntityNotFoundException();
        }

        internalList.remove(index);
    }

    public void setEntities(UniqueEntityList replacement) {
        requireNonNull(replacement);

        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code entities}.
     * {@code entities} must not contain duplicate entities.
     */
    public void setEntities(List<Pair<Integer, Entity>> entities) {
        requireNonNull(entities);

        if (!entitiesAreUnique(entities.stream().map(Pair::getValue).collect(toList()))) {
            throw new DuplicateEntityException();
        }

        internalList.setAll(entities);
    }

    /**
     * Returns the entity with the corresponding ID.
     * An entity with the ID must exist.
     */
    public Entity get(int id) {
        int index = getIndexOf(id);
        if (index == -1) {
            throw new EntityNotFoundException();
        }

        return internalList.get(index).getValue();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Pair<Integer, Entity>> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Pair<Integer, Entity>> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueEntityList // instanceof handles nulls
                        && internalList.equals(((UniqueEntityList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code entities} contains only unique entities.
     */
    private boolean entitiesAreUnique(List<Entity> entities) {
        for (int i = 0; i < entities.size() - 1; i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                if (entities.get(i).equals(entities.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
