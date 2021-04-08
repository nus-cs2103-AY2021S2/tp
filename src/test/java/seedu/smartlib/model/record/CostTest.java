package seedu.smartlib.model.record;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CostTest {

    @Test
    public void constructor() {
        // EP: negative overdue hours -> throws assertion error
        assertThrows(AssertionError.class, () -> new Cost(-1));
        assertThrows(AssertionError.class, () -> new Cost(-Integer.MAX_VALUE));

        // EP: zero overdue hours -> does not throw error
        assertDoesNotThrow(() -> new Cost(0));

        // EP: positive overdue hours -> does not throw error
        assertDoesNotThrow(() -> new Cost(1));
        assertDoesNotThrow(() -> new Cost(Integer.MAX_VALUE));
    }

    @Test
    public void getCost() {
        // EP: zero overdue hours
        assertEquals(0, new Cost(0).getCost());

        // EP: positive overdue hours
        assertEquals(1 * Cost.RATES_PER_HOUR, new Cost(1).getCost());
        assertEquals(Integer.MAX_VALUE * Cost.RATES_PER_HOUR,
                new Cost(Integer.MAX_VALUE).getCost());
    }

    @Test
    public void tostring() {
        Cost cost = new Cost(0);
        Cost costCopy = new Cost(0);
        Cost cost2 = new Cost(1);

        // same object -> returns same toString value
        assertEquals(cost.toString(), cost.toString());

        // same values -> returns same toString value
        assertEquals(cost.toString(), costCopy.toString());

        // different values -> returns different toString value
        assertNotEquals(cost.toString(), cost2.toString());
    }

    @Test
    public void equals() {
        Cost cost = new Cost(0);
        Cost costCopy = new Cost(0);
        Cost cost2 = new Cost(1);

        // null -> returns false
        assertFalse(cost.equals(null));

        // different types -> returns false
        assertFalse(cost.equals(0.5f));
        assertFalse(cost.equals(" "));

        // same object -> returns true
        assertTrue(cost.equals(cost));

        // same values -> returns true
        assertTrue(cost.equals(costCopy));

        // different values -> returns false
        assertFalse(cost.equals(cost2));
    }

}
