package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class AssignmentTest {
    private final Description description1 = new Description("test 1");
    private final Description description2 = new Description("test 2");
    private final LocalDateTime date1 = LocalDateTime.parse("11/12/2021 1900",
                                                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    private final LocalDateTime date2 = LocalDateTime.parse("12/12/2021 1900",
                                                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    private final Tag tag = new Tag("CS2103");
    private final Assignment assignment = new Assignment(description1, date1, tag);
    private final Assignment editedAssignmentDiffDate = new Assignment(description1, date2, tag);
    private final Assignment editedAssignmentDiffDescription = new Assignment(description2, date1, tag);
    private final Assignment diffAssignment = new Assignment(description2, date2, tag);

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
    public void setDescription() {
        Assignment assignmentCopy = new Assignment(description1, date1, tag);
        // description changed to description2
        assertEquals(assignmentCopy.setDescription(description2), editedAssignmentDiffDescription);
    }

    @Test
    public void setDeadline() {
        Assignment assignmentCopy = new Assignment(description1, date1, tag);
        // description changed to description2
        assertEquals(assignmentCopy.setDeadline(date2), editedAssignmentDiffDate);
    }

    @Test
    public void isDone() {
        // assignment is not done
        Assignment assignment = new Assignment(description1, date1, tag, false);
        assertEquals(assignment.isDone(), "[ ]");

        // assignment is done
        assignment = new Assignment(description2, date2, tag, true);
        assertEquals(assignment.isDone(), "[X]");
    }

    @Test
    public void toggleDoneStatus() {
        Assignment assignment = new Assignment(description2, date1, tag);
        // assignment is not done, toggled to done
        assignment.toggleDoneStatus();
        assertEquals(assignment.isDone(), "[X]");

        // assignment toggle to done
        assignment.toggleDoneStatus();
        assertEquals(assignment.isDone(), "[ ]");
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
