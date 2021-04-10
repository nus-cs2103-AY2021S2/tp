package seedu.module.commons.core.optionalfield;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class OptionalFieldTest {

    @Test
    void equalsTest() {
        OptionalField<String> testObjectOne = new OptionalField<>("one");

        // same values -> returns true
        assertTrue(testObjectOne.equals(testObjectOne));

        // null -> returns false
        assertFalse(testObjectOne.equals(null));

        // different type -> returns false
        assertFalse(testObjectOne.equals(1));

        // one contains null, the other one does not -> returns false
        OptionalField<String> testObjectNull = new OptionalField<>(null);
        assertFalse(testObjectOne.equals(testObjectNull));
        assertFalse(testObjectNull.equals(testObjectOne));

        // contains different object -> returns false
        OptionalField<String> testObjectTwo = new OptionalField<>("two");
        assertFalse(testObjectOne.equals(testObjectTwo));

        // contains same object -> returns true
        OptionalField<String> testObjectSame = new OptionalField<>("one");
        assertTrue(testObjectOne.equals(testObjectSame));

    }

    @Test
    void toStringTest() {
        OptionalField<String> testObjectNull = new OptionalField<>(null);
        OptionalField<String> testObjectOne = new OptionalField<>("one");

        assertEquals(testObjectNull.toString(), OptionalField.FIELD_IS_NULL);
        assertEquals(testObjectOne.toString(), "one");
    }

    @Test
    void getField() {
        OptionalField<String> testObjectNull = new OptionalField<>(null);
        OptionalField<String> testObjectOne = new OptionalField<>("one");

        assertThrows(AssertionError.class, () -> testObjectNull.getField());
        assertEquals(testObjectOne.getField(), "one");
    }
}
