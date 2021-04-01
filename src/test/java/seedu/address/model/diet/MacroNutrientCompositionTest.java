package seedu.address.model.diet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MacroNutrientCompositionTest {

    @Test
    public void constructor_invalidValues_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new MacroNutrientComposition(0, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new MacroNutrientComposition(10, 20, 30));
        assertThrows(IllegalArgumentException.class, () -> new MacroNutrientComposition(100, 20, 30));
        assertThrows(IllegalArgumentException.class, () -> new MacroNutrientComposition(110, 0, -10));
    }

    @Test
    public void isValidComposition() {
        assertFalse(MacroNutrientComposition.isValidComposition(0, 10, 20));
        assertFalse(MacroNutrientComposition.isValidComposition(100, 10, 20));
        assertFalse(MacroNutrientComposition.isValidComposition(-10, 130, -20));

        assertTrue(MacroNutrientComposition.isValidComposition(100, 0, 0));
        assertTrue(MacroNutrientComposition.isValidComposition(40, 30, 30));
        assertTrue(MacroNutrientComposition.isValidComposition(25, 50, 25));
    }

}
