package seedu.address.model.group;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import seedu.address.model.group.exceptions.DuplicateGroupException;
import seedu.address.model.group.exceptions.GroupNotFoundException;
import seedu.address.model.person.Name;

/**
 * A HashMap mapping groupName to Group.
 * Supports a minimal set of list operations.
 *
 * @see Group#isSameGroup(Group)
 */
public class GroupHashMap {
    public static final Name DEFAULT_GROUP_NAME = new Name("All");

    private final ObservableMap<Name, Group> internalMap = FXCollections.observableHashMap();
    private final ObservableMap<Name, Group> internalUnmodifiableMap =
            FXCollections.unmodifiableObservableMap(internalMap);

    public GroupHashMap() {
        internalMap.put(DEFAULT_GROUP_NAME, new Group(DEFAULT_GROUP_NAME));
    }
    /**
     * Returns true if the map contains an equivalent Name as the given argument.
     */
    public boolean contains(Name toCheck) {
        requireNonNull(toCheck);
        return internalMap.containsKey(toCheck);
    }

    /**
     * Adds a group to the hashmap.
     * The group must not already exist in the hashmap.
     */
    public void add(Group toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd.getName())) {
            throw new DuplicateGroupException();
        }
        internalMap.put(toAdd.getName(), toAdd);
    }

    /**
     * Replace given person name with new name in all groups.
     */
    public void replacePerson(Name personToReplace, Name newPersonName) {
        internalMap.values().stream().forEach(g -> g.setPersonName(personToReplace, newPersonName));
    }

    /**
     * Add Person Name to the default group that will contain all persons.
     */
    public void addPersonName(Name personName) {
        internalMap.get(DEFAULT_GROUP_NAME).addPersonName(personName);
    }

    /**
     * Add List of Person Names to the default group that will contain all persons
     */
    public void addPersonNames(List<Name> personNames) {
        personNames.stream().forEach(p -> addPersonName(p));
    }

    /**
     * Delete all instances of personName in all groups
     */
    public void removePersonName(Name personName) {
        internalMap.values().stream().forEach(g -> g.removePersonName(personName));
    }

    /**
     * Removes the equivalent group from the list.
     * The group must exist in the list.
     */
    public void remove(Group toRemove) {
        requireNonNull(toRemove);
        if (!contains(toRemove.getName())) {
            throw new GroupNotFoundException();
        }
        internalMap.remove(toRemove.getName());
    }

    /**
     * Replaces the group for {@code groupName} in the map with {@code editedGroup}.
     * {@code groupName} must exist in the hashmap.
     */
    public void setGroup(Name groupName, Group editedGroup) {
        requireAllNonNull(groupName, editedGroup);

        Group group = internalMap.get(groupName);
        if (group == null) {
            throw new GroupNotFoundException();
        }
        internalMap.remove(groupName);
        internalMap.put(groupName, editedGroup);
    }

    /**
     * Replaces the contents of this map with {@code groupHashMap}.
     */
    public void setGroups(HashMap<Name, Group> groupHashMap) {
        requireAllNonNull(groupHashMap);
        internalMap.clear();
        internalMap.putAll(groupHashMap);
    }

    /**
     * Returns the backing Map as an unmodifiable {@code ObservableMap}.
     */
    public ObservableMap<Name, Group> asUnmodifiableObservableMap() {
        return internalUnmodifiableMap;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupHashMap // instanceof handles nulls
                && internalMap.equals(((GroupHashMap) other).internalMap));
    }

    @Override
    public int hashCode() {
        return internalMap.hashCode();
    }
}
