package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class PriorityTagTest {

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new PriorityTag(invalidName));
    }

    @Test
    public void isValidPriorityTag() {
        // valid test
        assertTrue(PriorityTag.validateTag("LOW"));
        assertTrue(PriorityTag.validateTag("MEDIUM"));
        assertTrue(PriorityTag.validateTag("HIGH"));

        //invalid test
        assertFalse(PriorityTag.validateTag("Hello")); //alphabets
        assertFalse(PriorityTag.validateTag("")); // empty string
        assertFalse(PriorityTag.validateTag("***")); //special characters
        assertFalse(PriorityTag.validateTag("???hello")); //special characters and alphabets
        assertFalse(PriorityTag.validateTag("123")); //numerics
        assertFalse(PriorityTag.validateTag("123test")); //numerics and alphabets

        assertFalse(PriorityTag.validateTag("MED")); // similar cases
        assertFalse(PriorityTag.validateTag("HiGH")); // similar cases
        assertFalse(PriorityTag.validateTag("LoW")); // similar cases

    }





}
