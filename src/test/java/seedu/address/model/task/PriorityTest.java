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
        assertThrows(IllegalArgumentException.class, () -> Priority.valueOf(""));

        assertThrows(IllegalArgumentException.class, () -> Priority.valueOf("high"));
        assertThrows(IllegalArgumentException.class, () -> Priority.valueOf("medium"));
        assertThrows(IllegalArgumentException.class, () -> Priority.valueOf("low"));

        assertThrows(IllegalArgumentException.class, () -> Priority.valueOf("High"));
        assertThrows(IllegalArgumentException.class, () -> Priority.valueOf("Medium"));
        assertThrows(IllegalArgumentException.class, () -> Priority.valueOf("Low"));
    }

    @Test
    public void isValidPriorityValue() {
        // invalid priority
        assertFalse(Priority.isValidValue(null));
        assertFalse(Priority.isValidValue("")); // empty string
        assertFalse(Priority.isValidValue("extreme")); // No such value

        assertFalse(Priority.isValidValue("High"));
        assertFalse(Priority.isValidValue("Medium"));
        assertFalse(Priority.isValidValue("Low"));
        assertFalse(Priority.isValidValue("Unassigned"));

        assertFalse(Priority.isValidValue("HIGH"));
        assertFalse(Priority.isValidValue("MEDIUM"));
        assertFalse(Priority.isValidValue("LOW"));
        assertFalse(Priority.isValidValue("UNASSIGNED"));

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

        assertNotEquals(Priority.valueOf("HIGH").toString(), "High");
        assertNotEquals(Priority.valueOf("MEDIUM").toString(), "Medium");
        assertNotEquals(Priority.valueOf("LOW").toString(), "Low");

        assertNotEquals(Priority.valueOf("HIGH").toString(), "HIGH");
        assertNotEquals(Priority.valueOf("MEDIUM").toString(), "MEDIUM");
        assertNotEquals(Priority.valueOf("LOW").toString(), "LOW");
    }

    @Test
    public void getEnumName() {
        assertEquals(Priority.valueOf("LOW").getEnumName(), "Priority");
        assertNotEquals(Priority.valueOf("LOW").getEnumName(), "priority");
    }

    @Test
    public void isUnassigned() {
        assertThrows(NullPointerException.class, () -> Priority.isUnassigned(null));

        assertTrue(Priority.isUnassigned("unassigned"));
        assertFalse(Priority.isUnassigned("Unassigned"));
    }

}
