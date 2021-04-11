package seedu.address.model.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IdealWeightTest {
    @Test
    public void isValidIdealWeight_validWeight_true() {
        assertTrue(IdealWeight.isValidIdealWeight("1"));
        assertTrue(IdealWeight.isValidIdealWeight("50"));
    }

    @Test
    public void isValidIdealWeight_invalidWeight_false() {
        assertFalse(IdealWeight.isValidIdealWeight("-1"));
        assertFalse(IdealWeight.isValidIdealWeight("0"));
    }
}
