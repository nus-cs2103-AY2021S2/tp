package seedu.address.model.plan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    public static final Description VALID_DESCRIPTION_SOFTWARE_ENGINEERING = new Description(
            "software engineering route");
    public static final Description VALID_DESCRIPTION_COMPUTER_NETWORKING = new Description(
            "computer networking route");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid addresses
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only

        // valid addresses
        assertTrue(Description.isValidDescription("software engineering"));
        assertTrue(Description.isValidDescription("s")); // one character
        assertTrue(Description.isValidDescription("not too sure whether this "
                + "plan will be viable but let's give it a try")); // long address
    }

    @Test
    public void validToString() {
        assertEquals("software engineering route", VALID_DESCRIPTION_SOFTWARE_ENGINEERING.toString());

    }

    @Test
    public void equals() {
        // different description
        assertFalse(VALID_DESCRIPTION_SOFTWARE_ENGINEERING.equals(VALID_DESCRIPTION_COMPUTER_NETWORKING));

        // similar description
        Description similarDescription = new Description(VALID_DESCRIPTION_SOFTWARE_ENGINEERING.value);
        assertTrue(VALID_DESCRIPTION_SOFTWARE_ENGINEERING.equals(similarDescription));
    }

    @Test
    public void hashCodeCheck() {
        // same hashcode
        Description temp = VALID_DESCRIPTION_SOFTWARE_ENGINEERING;
        assertEquals(temp.hashCode(), VALID_DESCRIPTION_SOFTWARE_ENGINEERING.hashCode());

        // different hashcode
        assertNotEquals(VALID_DESCRIPTION_COMPUTER_NETWORKING.hashCode(),
                VALID_DESCRIPTION_SOFTWARE_ENGINEERING.hashCode());
    }

}
