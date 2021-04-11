package seedu.address.model.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AgeTest {

    @Test
    public void isValidAge_validAge_true() {
        assertTrue(Age.isValidAge("50"));
        assertTrue(Age.isValidAge("100"));
        assertTrue(Age.isValidAge("1"));
    }

    @Test
    public void isValidAge_invalidAge_false() {
        assertFalse(Age.isValidAge("101"));
        assertFalse(Age.isValidAge("-1"));
        assertFalse(Age.isValidAge("9999"));
    }


}
