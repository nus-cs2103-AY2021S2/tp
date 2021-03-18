package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class AssignmentTest {
    private Description description1 = new Description("test 1");
    private Description description2 = new Description("test 2");
    private LocalDateTime date1 = LocalDateTime.parse("11/12/2021 1900",
                                                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    private LocalDateTime date2 = LocalDateTime.parse("12/12/2021 1900",
                                                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    private Tag tag = new Tag("CS2103");
    private Assignment assignment = new Assignment(description1, date1, tag);
    private Assignment editedAssignmentDiffDate = new Assignment(description1, date2, tag);
    private Assignment editedAssignmentDiffDescription = new Assignment(description2, date1, tag);
    private Assignment diffAssignment = new Assignment(description2, date2, tag);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Assignment(null, date1, tag)); // null description
        assertThrows(NullPointerException.class, () -> new Assignment(description1, null, tag)); // null date
        assertThrows(NullPointerException.class, () -> new Assignment(null, null, tag)); // both null
    }

    @Test
    public void isSameAssignment() {
        // same object -> returns true
        assertTrue(assignment.isSameAssignment(assignment));

        // null -> returns false
        assertFalse(assignment.isSameAssignment(null));

        // same description different date -> returns false
        assertFalse(assignment.isSameAssignment(editedAssignmentDiffDate));

        // same date different description -> returns false
        assertFalse(assignment.isSameAssignment(editedAssignmentDiffDescription));

        // different assignment -> returns false
        assertFalse(assignment.isSameAssignment(diffAssignment));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Assignment assignmentCopy = new Assignment(description1, date1, tag);
        assertTrue(assignment.equals(assignmentCopy));

        // same object -> returns true
        assertTrue(assignment.equals(assignment));

        // null -> returns false
        assertFalse(assignment.equals(null));

        // different type -> returns false
        assertFalse(assignment.equals(5));

        // different assignment -> returns false
        assertFalse(assignment.equals(diffAssignment));

        // different description -> return false
        assertFalse(assignment.equals(editedAssignmentDiffDescription));

        // different date -> return false
        assertFalse(assignment.equals(editedAssignmentDiffDate));
    }

    @Test
    public void testToString() {
        // same object -> returns true
        assertTrue(assignment.toString().equals(assignment.toString()));

        // null -> returns false
        assertFalse(assignment.toString().equals(null));

        // different assignments -> return false
        assertFalse(assignment.toString().equals(diffAssignment));

        // different description -> return false
        assertFalse(assignment.toString().equals(editedAssignmentDiffDescription.toString()));

        // different date -> return false
        assertFalse(assignment.toString().equals(editedAssignmentDiffDate.toString()));
    }
}
