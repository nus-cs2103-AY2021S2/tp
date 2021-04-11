package seedu.address.model.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BmiTest {
    @Test
    public void isValidHeightOrWeight_validHeightOrWeight_true() {
        assertTrue(Bmi.isValidWeightOrHeight("160.5"));
        assertTrue(Bmi.isValidWeightOrHeight("1.1"));
    }

    @Test
    public void isValidHeightOrWeight_validHeightOrWeight_false() {
        assertFalse(Bmi.isValidWeightOrHeight("-1.0"));
        assertFalse(Bmi.isValidWeightOrHeight("0"));
        assertFalse(Bmi.isValidWeightOrHeight("-1"));
    }
}
