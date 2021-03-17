package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    }
}
