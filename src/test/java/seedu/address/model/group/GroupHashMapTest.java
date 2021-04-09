package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.exceptions.DuplicateGroupException;
import seedu.address.model.group.exceptions.GroupNotFoundException;
import seedu.address.testutil.GroupBuilder;

public class GroupHashMapTest {
    private GroupHashMap groupMap = new GroupHashMap();
    private GroupHashMap otherGroupMap = new GroupHashMap();
    private Group group = new GroupBuilder().build();

    @Test
    public void equals_sameGroupMap_returnsTrue() {
        groupMap = new GroupHashMap();
        assertTrue(groupMap.equals(groupMap));
    }

    @Test
    public void equals_diffGroupMapWithSameContent_returnsTrue() {
        groupMap = new GroupHashMap();
        otherGroupMap = new GroupHashMap();
        assertTrue(groupMap.equals(otherGroupMap));
    }

    @Test
    public void contains_null_throwsNullPointerException() {
        groupMap = new GroupHashMap();
        assertThrows(NullPointerException.class, () -> groupMap.contains(null));
    }

    @Test
    public void contains_groupNotInMap_returnsFalse() {
        groupMap = new GroupHashMap();
        assertFalse(groupMap.contains(group.getName()));
    }

    @Test
    public void contains_groupInMap_returnsTrue() {
        groupMap = new GroupHashMap();
        group = new GroupBuilder().build();
        groupMap.add(group);
        assertTrue(groupMap.contains(group.getName()));
    }

    @Test
    public void contains_groupWithSameIdentityFieldsInList_returnsTrue() {
        groupMap = new GroupHashMap();
        group = new GroupBuilder().build();
        groupMap.add(group);
        Group editedGroup = new GroupBuilder().withPersons(ALICE).build();
        assertTrue(groupMap.contains(editedGroup.getName()));
    }

    @Test
    public void add_duplicateGroup_throwsDuplicateGroupException() {
        groupMap = new GroupHashMap();
        group = new GroupBuilder().build();
        groupMap.add(group);
        assertThrows(DuplicateGroupException.class, () -> groupMap.add(group));
    }

    @Test
    public void hashCode_sameGroup_returnsTrue() {
        groupMap = new GroupHashMap();
        otherGroupMap = new GroupHashMap();
        assertTrue(groupMap.hashCode() == otherGroupMap.hashCode());
    }

    @Test
    public void remove_correctGroup_returnsTrue() {
        groupMap = new GroupHashMap();
        group = new GroupBuilder().build();
        otherGroupMap = new GroupHashMap();

        groupMap.add(group);
        groupMap.remove(group);

        assertTrue(groupMap.equals(otherGroupMap));
    }

    @Test
    public void remove_nonExistingGroup_throwsGroupNotFoundException() {
        groupMap = new GroupHashMap();
        group = new GroupBuilder().build();
        assertThrows(GroupNotFoundException.class, () -> groupMap.remove(group));
    }
}
