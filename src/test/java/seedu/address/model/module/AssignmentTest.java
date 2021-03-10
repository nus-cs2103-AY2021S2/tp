package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class AssignmentTest {
    private Description description1 = new Description("test 1");
    private Description description2 = new Description("test 2");
    private LocalDateTime date1 = LocalDateTime.parse("11/12/2021 1900",
                                                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    private LocalDateTime date2 = LocalDateTime.parse("12/12/2021 1900",
                                                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Assignment(null, date1)); // null description
        assertThrows(NullPointerException.class, () -> new Assignment(description1, null)); // null date
        assertThrows(NullPointerException.class, () -> new Assignment(null, null)); // both null
    }

    @Test
    public void isSameAssignment() {
        Assignment assignment = new Assignment(description1, date1);
        Assignment editedAssignmentDiffDate = new Assignment(description1, date2);
        Assignment editedAssignmentDiffDescription = new Assignment(description2, date1);
        Assignment diffAssignment = new Assignment(description2, date2);

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
        Assignment assignment1 = new Assignment(description1, date1);
        Assignment assignment2 = new Assignment(description2, date2);

        // same values -> returns true
        Assignment assignment1Copy = new Assignment(description1, date1);
        assertTrue(assignment1.equals(assignment1Copy));

        // same object -> returns true
        assertTrue(assignment1.equals(assignment1));

        // null -> returns false
        assertFalse(assignment1.equals(null));

        // different type -> returns false
        assertFalse(assignment1.equals(5));

        // different assignment -> returns false
        assertFalse(assignment1.equals(assignment2));
    }
}
