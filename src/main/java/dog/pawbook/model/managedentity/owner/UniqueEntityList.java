package dog.pawbook.model.managedentity.owner;

import static dog.pawbook.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

import java.util.Iterator;
import java.util.List;

import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.owner.exceptions.DuplicateEntityException;
import dog.pawbook.model.managedentity.owner.exceptions.EntityNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

/**
 * A list of owners that enforces uniqueness between its elements and does not allow nulls.
 * A owner is considered unique by comparing using {@code Owner#isSameOwner(Owner)}. As such, adding and updating of
 * owners uses Owner#isSameOwner(Owner) for equality so as to ensure that the owner being added or updated is
 * unique in terms of identity in the UniqueOwnerList. However, the removal of a owner uses Owner#equals(Object) so
 * as to ensure that the owner with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Owner#isSameEntity(Entity)
 */

public class UniqueEntityList implements Iterable<Pair<Integer, Entity>> {

    private int newId = 1; // id is never 0
    private final ObservableList<Pair<Integer, Entity>> internalList = FXCollections.observableArrayList();
    private final ObservableList<Pair<Integer, Entity>> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Checks if list contains an owner of a particular id.
     *
     * @param id owner id.
     * @return boolean of whether owner exists.
     */
    public boolean contains(int id) {
        return internalList.stream().anyMatch(p -> p.getKey() == id);
    }

    /**
     * Checks if list contains a particular owner
     *
     * @param toCheck owner entity
     * @return boolean of whether owner exists.
     */
    public boolean contains(Entity toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(p -> toCheck.isSameEntity(p.getValue()));
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
    public void add(Entity toAdd) {
        requireNonNull(toAdd);

        if (contains(toAdd)) {
            throw new DuplicateEntityException();
        }
        internalList.add(new Pair<>(genID(), toAdd));
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

        // keep changing the id if clash occurs
        while (contains(newId)) {
            ++newId;
        }
    }

    /**
     * Replaces the owner {@code target} in the list with {@code editedEntity}.
     * {@code target} must exist in the list.
     * The entity identity of {@code editedEntity} must not be the same as another existing entity in the list.
     */
    public void setEntity(int targetId, Entity editedEntity) {
        requireAllNonNull(targetId, editedEntity);

        int index = getIndexOf(targetId);
        if (index == -1) {
            throw new EntityNotFoundException();
        }
        boolean containsToAdd = internalList.stream().anyMatch(p -> p.getValue().equals(editedEntity));
        if (!internalList.get(index).getValue().isSameEntity(editedEntity) && containsToAdd) {
            throw new DuplicateEntityException();
        }

        int currentId = internalList.get(index).getKey();

        internalList.set(index, new Pair<>(currentId, editedEntity));
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
        requireAllNonNull(entities);
        if (!entitiesAreUnique(entities.stream().map(Pair::getValue).collect(toList()))) {
            throw new DuplicateEntityException();
        }

        internalList.setAll(entities);
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
                if (entities.get(i).isSameEntity(entities.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
