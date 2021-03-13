package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.exceptions.DuplicateGroupException;
import seedu.address.testutil.GroupBuilder;

public class GroupHashMapTest {
    private final GroupHashMap groupMap = new GroupHashMap();
    private final Group group = new GroupBuilder().build();

    @Test
    public void contains_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> groupMap.contains(null));
    }

    @Test
    public void contains_groupNotInMap_returnsFalse() {
        assertFalse(groupMap.contains(group));
    }

    @Test
    public void contains_groupInMap_returnsTrue() {
        groupMap.add(group);
        assertTrue(groupMap.contains(group));
    }

    @Test
    public void contains_groupWithSameIdentityFieldsInList_returnsTrue() {
        groupMap.add(group);
        Group editedGroup = new GroupBuilder().withPersons(ALICE).build();
        assertTrue(groupMap.contains(editedGroup));
    }

    @Test
    public void add_duplicateGroup_throwsDuplicateGroupException() {
        groupMap.add(group);
        assertThrows(DuplicateGroupException.class, () -> groupMap.add(group));
    }

}
