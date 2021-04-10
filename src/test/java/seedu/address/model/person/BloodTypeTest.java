package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class BloodTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BloodType(null));
    }

    @Test
    public void constructor_invalidBloodType_throwsIllegalArgumentException() {
        String invalidBloodType = "";
        assertThrows(IllegalArgumentException.class, () -> new BloodType(invalidBloodType));
    }

    @Test
    public void isValidBloodType() {
        // null blood type
        assertThrows(NullPointerException.class, () -> BloodType.isValidBloodType(null));

        // invalid blood types
        assertFalse(BloodType.isValidBloodType("")); // empty string
        assertFalse(BloodType.isValidBloodType(" ")); // spaces only
        assertFalse(BloodType.isValidBloodType("91")); // only numbers
        assertFalse(BloodType.isValidBloodType("AB")); // no Rh value specified
        assertFalse(BloodType.isValidBloodType("+")); // no blood group specified
        assertFalse(BloodType.isValidBloodType("O -")); // space between blood group and Rh value

        // valid blood types
        assertTrue(BloodType.isValidBloodType("A-")); // valid blood types
        assertTrue(BloodType.isValidBloodType("B+"));
        assertTrue(BloodType.isValidBloodType("AB-"));
        assertTrue(BloodType.isValidBloodType("O+"));
    }

}
