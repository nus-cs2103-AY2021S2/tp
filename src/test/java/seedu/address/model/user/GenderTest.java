package seedu.address.model.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GenderTest {
    @Test
    public void isValidGender_validGender_true() {
        assertTrue(Gender.isValidGender("M"));
        assertTrue(Gender.isValidGender("F"));
    }

    @Test
    public void isValidGender_invalidGender_false() {
        assertFalse(Gender.isValidGender("-1"));
        assertFalse(Gender.isValidGender("T"));
        assertFalse(Gender.isValidGender("x"));
    }
}
