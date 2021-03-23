package dog.pawbook.model.managedentity;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.exceptions.BrokenReferencesException;
import dog.pawbook.model.managedentity.exceptions.DuplicateEntityException;
import dog.pawbook.model.managedentity.exceptions.EntityNotFoundException;
import dog.pawbook.model.managedentity.owner.Owner;
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
    public boolean contains(Integer id) {
        return internalList.stream().map(Pair::getKey).anyMatch(id::equals);
    }

    /**
     * Checks if list contains a particular entity
     *
     * @param toCheck entity
     * @return boolean of whether entity exists.
     */
    public boolean contains(Entity toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().map(Pair::getValue).anyMatch(toCheck::equals);
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
     * Must manually keep references of other related entities updated to avoid broken linkages.
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

        Pair<Integer, Entity> editedPair = new Pair<>(targetID, editedEntity);
        if (!referencedIdValid(editedPair, internalList)) {
            throw new BrokenReferencesException();
        }

        internalList.set(index, new Pair<>(targetID, editedEntity));
    }

    /**
     * Removes the entity with the given ID.
     * Must manually keep references of other related entities updated to avoid broken linkages.
     */
    public void remove(int toRemoveId) {
        int index = getIndexOf(toRemoveId);
        if (index == -1) {
            throw new EntityNotFoundException();
        }

        internalList.remove(index);
    }

    /**
     * Replace all entities stored with ones from inside {@code replacement}
     */
    public void setEntities(UniqueEntityList replacement) {
        requireNonNull(replacement);

        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code entities}.
     * {@code entities} must not contain duplicate entities, and all entries must have valid references to one another.
     */
    public void setEntities(List<Pair<Integer, Entity>> entities) {
        requireNonNull(entities);

        if (!entitiesAreUnique(entities.stream().map(Pair::getValue).collect(toList()))) {
            throw new DuplicateEntityException();
        }

        if (!entitiesHaveValidReferences(entities)) {
            throw new BrokenReferencesException();
        }

        internalList.setAll(entities);
    }

    /**
     * Checks if all entities' reference to other entities are valid.
     */
    private static boolean entitiesHaveValidReferences(List<Pair<Integer, Entity>> entities) {
        // check that all owners' dogs are mutually exclusive
        boolean noDogOverlap = entities.stream().map(Pair::getValue)
                .filter(Owner.class::isInstance).map(Owner.class::cast)
                .map(Owner::getDogIdSet)
                .flatMap(Collection::stream)
                .allMatch(new HashSet<>()::add);

        if (!noDogOverlap) {
            return false;
        }

        // finally check all the mutual links
        return entities.stream().allMatch(pair -> referencedIdValid(pair, entities));
    }

    /**
     * Checks if {@code referrer}'s link to other entities are invalid or not mutual when necessary.
     */
    private static boolean referencedIdValid(Pair<Integer, Entity> focus, List<Pair<Integer, Entity>> entities) {
        int focusID = focus.getKey();
        Entity focusEntity = focus.getValue();
        if (focusEntity instanceof Owner) {
            Owner owner = (Owner) focusEntity;
            Set<Integer> dogIdSet = owner.getDogIdSet();
            for (int dogId : dogIdSet) {
                if (dogId < 1) {
                    return false;
                }
                List<Dog> dogs = entities.stream().filter(p -> p.getKey() == dogId).map(Pair::getValue)
                        .filter(Dog.class::isInstance).map(Dog.class::cast).collect(toList());
                if (dogs.size() == 0) {
                    return false;
                }
                assert dogs.size() == 1 : "There should only be exactly one matching dog";
                Dog dog = dogs.get(0);
                if (dog.getOwnerId() != focusID) {
                    return false;
                }
            }
            return true;
        } else if (focusEntity instanceof Dog) {
            Dog dog = (Dog) focusEntity;
            int ownerId = dog.getOwnerId();
            if (ownerId < 1) {
                return false;
            }
            List<Owner> owners = entities.stream().filter(p -> p.getKey() == ownerId).map(Pair::getValue)
                    .filter(Owner.class::isInstance).map(Owner.class::cast).collect(toList());
            if (owners.size() == 0) {
                return false;
            }
            assert owners.size() == 1 : "There should only be exactly one matching owner";
            Owner owner = owners.get(0);
            return owner.getDogIdSet().contains(focusID);
        }
        throw new AssertionError("Unknown entity type to verify!");
    }

    /**
     * Validate all links to other IDs from all entities.
     */
    public boolean validateReferences() {
        return entitiesHaveValidReferences(internalList);
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
