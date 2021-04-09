package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.alias.AliasUtil.VALID_ALIAS_1;

import org.junit.jupiter.api.Test;

import seedu.address.model.alias.Alias;
import seedu.address.model.alias.AliasMapping;

public class AliasTest {

    public static final String VALID_ALIAS_NAME = "valid";
    public static final String INVALID_ALIAS_NAME = "inv@lid";
    public static final String VALID_COMMAND = "validCommand";

    @Test
    public void equals() {
        // same alias -> true
        assertEquals(VALID_ALIAS_1, VALID_ALIAS_1);

        // same argument -> true
        assertEquals(new Alias(VALID_ALIAS_NAME, VALID_COMMAND), new Alias(VALID_ALIAS_NAME, VALID_COMMAND));

        // null alias -> false
        assertNotEquals(VALID_ALIAS_1, null);

        // different instance -> false
        assertNotEquals(VALID_ALIAS_1, new AliasMapping());
    }

    @Test
    public void isValidName() {
        // valid name
        assertTrue(Alias.isValidName("valid")); // alphabets only
        assertTrue(Alias.isValidName("54110")); // numbers only
        assertTrue(Alias.isValidName("v4l1d")); // alphanumeric

        // blank name
        assertFalse(Alias.isValidName("")); // empty string
        assertFalse(Alias.isValidName(" ")); // spaces only

        // invalid name
        assertFalse(Alias.isValidName("inv@lid")); // special character
    }
}
