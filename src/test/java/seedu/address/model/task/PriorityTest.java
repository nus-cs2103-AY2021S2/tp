package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriorityTest {

    @Test
    public void null_enum_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Priority.valueOf(null));
    }

    @Test
    public void invalid_enum_throwsIllegalArgumentException() {
        String invalidPriority = "";
        assertThrows(IllegalArgumentException.class, () -> Priority.valueOf(invalidPriority));
    }

    @Test
    public void isValidPriorityValue() {
        // invalid priority
        assertFalse(Priority.isValidValue(null));
        assertFalse(Priority.isValidValue("")); // empty string
        assertFalse(Priority.isValidValue("extreme")); // No such value


        // valid description
        assertTrue(Priority.isValidValue("high")); // high value
        assertTrue(Priority.isValidValue("medium")); // medium value
        assertTrue(Priority.isValidValue("low")); // low value
        assertTrue(Priority.isValidValue("unassigned")); // unassigned value
    }

    @Test
    public void getPriority() {
        assertEquals(Priority.valueOf("HIGH").getPriority(), "high");
        assertNotEquals(Priority.valueOf("HIGH").getPriority(), "High");
        assertNotEquals(Priority.valueOf("HIGH").getPriority(), "HIGH");

        assertEquals(Priority.valueOf("MEDIUM").getPriority(), "medium");
        assertNotEquals(Priority.valueOf("MEDIUM").getPriority(), "Medium");
        assertNotEquals(Priority.valueOf("MEDIUM").getPriority(), "MEDIUM");


        assertEquals(Priority.valueOf("LOW").getPriority(), "low");
        assertNotEquals(Priority.valueOf("LOW").getPriority(), "Low");
        assertNotEquals(Priority.valueOf("LOW").getPriority(), "LOW");

        assertEquals(Priority.valueOf("UNASSIGNED").getPriority(), "unassigned");
        assertNotEquals(Priority.valueOf("UNASSIGNED").getPriority(), "Unassigned");
        assertNotEquals(Priority.valueOf("UNASSIGNED").getPriority(), "UNASSIGNED");
    }

    @Test
    public void printPriorityResult() {
        assertEquals(Priority.valueOf("HIGH").toString(), "high");
        assertEquals(Priority.valueOf("MEDIUM").toString(), "medium");
        assertEquals(Priority.valueOf("LOW").toString(), "low");
    }

    @Test
    public void getEnumName() {
        assertEquals(Priority.valueOf("LOW").getEnumName(), "Priority");
    }

    @Test
    public void isUnassigned() {
        assertTrue(Priority.isUnassigned("unassigned"));
        assertFalse(Priority.isUnassigned("Unassigned"));
    }

}
