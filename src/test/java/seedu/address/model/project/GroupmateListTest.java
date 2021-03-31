package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroupmates.ROXY;
import static seedu.address.testutil.TypicalGroupmates.SYLPH;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.groupmate.Groupmate;

public class GroupmateListTest {

    @Test
    public void constructor_empty_createEmptyGroupmateList() {
        GroupmateList emptyGroupmateList = new GroupmateList();
        assertTrue(emptyGroupmateList.getSortedGroupmates().isEmpty());
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GroupmateList(null));
    }

    @Test
    public void constructor_singleGroupmate_success() {
        ArrayList<Groupmate> groupmates = new ArrayList<>();
        groupmates.add(SYLPH);
        assertDoesNotThrow(() -> new GroupmateList(groupmates));
    }

    @Test
    public void getGroupmates_validGroupmateList_equalsOriginalList() {
        ArrayList<Groupmate> groupmates = new ArrayList<>();
        groupmates.add(SYLPH);
        GroupmateList groupmateList = new GroupmateList(groupmates);
        assertEquals(groupmates, groupmateList.getSortedGroupmates());
    }

    @Test
    public void size_validGroupmateList_correct() {
        ArrayList<Groupmate> groupmates = new ArrayList<>();
        groupmates.add(SYLPH);
        GroupmateList groupmateList = new GroupmateList(groupmates);
        assertEquals(groupmates.size(), groupmateList.size());
        groupmates.add(ROXY);
        groupmateList = new GroupmateList(groupmates);
        assertEquals(groupmates.size(), groupmateList.size());
    }

    @Test
    public void get_validGroupmateList_correct() {
        ArrayList<Groupmate> groupmates = new ArrayList<>();
        groupmates.add(ROXY);
        GroupmateList groupmateList = new GroupmateList(groupmates);
        assertEquals(groupmates.get(0), groupmateList.get(0));
        groupmates.add(SYLPH);
        groupmateList = new GroupmateList(groupmates);
        assertEquals(groupmates.get(0), groupmateList.get(0));
        assertEquals(groupmates.get(1), groupmateList.get(1));
    }

    @Test
    public void delete_validGroupmateList_correct() {
        ArrayList<Groupmate> groupmates = new ArrayList<>();
        groupmates.add(ROXY);
        groupmates.add(SYLPH);
        GroupmateList groupmateList = new GroupmateList(groupmates);
        assertEquals(2, groupmateList.size());
        groupmateList.delete(0);
        assertEquals(1, groupmateList.size());
        assertEquals(groupmateList.get(0), SYLPH);
        groupmateList.delete(0);
        assertEquals(0, groupmateList.size());
    }

    @Test
    public void getCopyOf_validGroupmateList_copyOfOriginal() {
        GroupmateList groupmateList = new GroupmateList();
        GroupmateList groupmateListCopy = groupmateList.getCopy();
        assertEquals(groupmateList, groupmateListCopy);
        assertNotSame(groupmateList, groupmateListCopy);
    }

}
