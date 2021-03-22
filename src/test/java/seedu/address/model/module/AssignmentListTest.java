package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class AssignmentListTest {
    private Description description1 = new Description("test 1");
    private Description description2 = new Description("test 2");
    private LocalDateTime date1 = LocalDateTime.parse("11/12/2021 1900",
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    private LocalDateTime date2 = LocalDateTime.parse("12/12/2021 1900",
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    private Tag tag = new Tag("CS2103");
    private Assignment assignment1 = new Assignment(description1, date1, tag);
    private Assignment assignment2 = new Assignment(description2, date2, tag);
    private List<Assignment> assignments = new ArrayList<>();

    @Test
    public void add() {
        AssignmentList list = new AssignmentList();
        list.add(assignment1);
        list.add(assignment2);

        // size of list is 2
        assertTrue(list.size() == 2);

        // assignment at index 0 is assignment1
        assertTrue(list.get(0).equals(assignment1));
        assertFalse(list.get(0).equals(assignment2));

        // assignment at index 1 is assignment2
        assertTrue(list.get(1).equals(assignment2));
        assertFalse(list.get(1).equals(assignment1));

        // AssignmentList created using different constructors, same attributes -> return true
        assignments.add(assignment1);
        assignments.add(assignment2);
        assertTrue(list.equals(new AssignmentList(assignments)));
    }

    @Test
    public void equals() {
        AssignmentList list = new AssignmentList();
        list.add(assignment1);
        list.add(assignment2);

        // same instance -> return true
        assertTrue(list.equals(list));

        // null -> returns false
        assertFalse(list.equals(null));

        // different instance -> return false
        assertFalse(list.equals(2));

        // different list size -> return false
        AssignmentList list2 = new AssignmentList();
        list2.add(assignment2);
        assertFalse(list.equals(list2));

        // different sequence -> return false
        list2.add(assignment1);
        assertFalse(list.equals(list2));

        // different assignments -> return false
        Assignment assignment3 = new Assignment(description2, date1, tag);
        list2.delete(0);
        list2.add(assignment3);
        assertFalse(list.equals(list2));

        // same attributes -> return true
        list2.delete(1);
        list2.add(assignment2);
        assertTrue(list.equals(list2));
    }

    @Test
    public void delete() {
        AssignmentList list = new AssignmentList();
        list.add(assignment1);
        list.add(assignment2);
        Assignment deleted = list.delete(1);

        // size of list is 1
        assertTrue(list.size() == 1);

        // deleted assignment is assignment2
        assertTrue(deleted.equals(assignment2));
        assertFalse(deleted.equals(assignment1));

        // assignment in list is assignment1
        assertTrue(list.get(0).equals(assignment1));
        assertFalse(list.get(0).equals(assignment2));

        // no other assignment in list
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));

        list.add(assignment2);
        deleted = list.delete(assignment1);

        // deleted assignment is assignment 2 -> returns true
        assertTrue(assignment1.equals(deleted));

        // size of list is 1 -> return true
        assertTrue(list.size() == 1);

        // remaining assignment in list is assignment 1 -> returns true
        assertTrue(assignment2.equals(list.get(0)));
    }

    @Test
    public void getIndex() {
        AssignmentList list = new AssignmentList();

        // empty list -> returns -1
        assertEquals(-1, list.getIndex(assignment1));

        // list containing assignment at index 0 with list size 1-> returns 0
        list.add(assignment1);
        assertEquals(list.getIndex(assignment1), 0);
        assertFalse(list.getIndex(assignment1) != 0);

        // list does not contain assignment -> returns -1
        assertEquals(-1, list.getIndex(assignment2));

        // list contains assignment at index 1 -> returns 1
        list.add(assignment2);
        assertEquals(1, list.getIndex(assignment2));

        // list contains assignment at index 0 -> returns 0
        assertEquals(0, list.getIndex(assignment1));

        // list updated such that assignment is at index 0 -> returns 0
        list.delete(0);
        assertEquals(0, list.getIndex(assignment2));
    }

    @Test
    public void contains() {
        AssignmentList list = new AssignmentList();

        // list is empty -> return false
        assertFalse(list.contains(assignment1));

        // list contains assignment -> returns true
        list.add(assignment1);
        assertTrue(list.contains(assignment1));

        // list doesn't contain assignment -> return false
        assertFalse(list.contains(assignment2));

        // list with multiple assignments
        list.add(assignment2);

        // list contains assignment -> return true
        assertTrue(list.contains(assignment1));
        assertTrue(list.contains(assignment2));

        // null -> returns false
        assertFalse(list.contains(null));
    }

    @Test
    public void getAssignment() {
        AssignmentList list = new AssignmentList();
        List<Assignment> assignments = new ArrayList<>();

        // empty list -> returns true
        assertTrue(list.getAssignments().equals(assignments));

        // null -> returns false
        assertFalse(list.getAssignments().equals(null));

        // list return is instance of List class -> return true
        assertTrue(list.getAssignments() instanceof List);

        // list returned is the same -> returns true
        list.add(assignment1);
        list.add(assignment2);
        assignments.add(assignment1);
        assignments.add(assignment2);
        assertTrue(list.getAssignments().equals(assignments));
    }
}
