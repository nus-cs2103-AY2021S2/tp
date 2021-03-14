package seedu.address.model.group;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;

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

    private final ObservableMap<Name, Group> internalMap = FXCollections.observableHashMap();

    /**
     * Returns true if the map contains an equivalent group as the given argument.
     */
    public boolean contains(Group toCheck) {
        requireNonNull(toCheck);
        return internalMap.containsKey(toCheck.getName());
    }

    /**
     * Adds a group to the hashmap.
     * The group must not already exist in the hashmap.
     */
    public void add(Group toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateGroupException();
        }
        internalMap.put(toAdd.getName(), toAdd);
    }


    /**
     * Removes the equivalent group from the list.
     * The group must exist in the list.
     */
    public void remove(Group toRemove) {
        requireNonNull(toRemove);
        if (!contains(toRemove)) {
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
        internalMap.replace(groupName, editedGroup);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setGroups(HashMap<Name, Group> groupHashMap) {
        requireAllNonNull(groupHashMap);
        internalMap.clear();
        internalMap.putAll(groupHashMap);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableMap<Name, Group> asUnmodifiableObservableMap() {
        return FXCollections.unmodifiableObservableMap(internalMap);
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
