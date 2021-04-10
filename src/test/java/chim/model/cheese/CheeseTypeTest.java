package chim.model.cheese;

import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_BRIE;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_CAMEMBERT;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_FETA;
import static chim.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CheeseTypeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> CheeseType.getCheeseType(null));
    }

    @Test
    public void constructor_emptyString_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> CheeseType.getCheeseType(invalidName));
    }

    @Test
    public void constructor_validCheeseName_returnsTrue() {
        CheeseType brieCheeseType = CheeseType.getCheeseType(VALID_CHEESE_TYPE_BRIE);
        assertEquals(brieCheeseType.value, VALID_CHEESE_TYPE_BRIE);
    }

    @Test
    public void constructor_verifySameCheeseType_returnsTrue() {
        CheeseType fetaCheeseType = CheeseType.getCheeseType(VALID_CHEESE_TYPE_FETA);
        assertEquals(fetaCheeseType.value, VALID_CHEESE_TYPE_FETA);

        // Creates new string with same value as Feta
        String newFetaString = new String(VALID_CHEESE_TYPE_FETA);
        CheeseType newFetaCheeseType = CheeseType.getCheeseType(newFetaString);
        assertTrue(fetaCheeseType == newFetaCheeseType);
    }

    @Test
    public void isValidName() {
        // Null name
        assertThrows(NullPointerException.class, () -> CheeseType.getCheeseType(null));

        // Invalid name
        assertFalse(CheeseType.isValidType("")); // empty string
        assertFalse(CheeseType.isValidType(" ")); // spaces only
        assertFalse(CheeseType.isValidType("      ")); // multiple spaces only

        // Valid name
        assertTrue(CheeseType.isValidType(VALID_CHEESE_TYPE_FETA));
        assertTrue(CheeseType.isValidType(VALID_CHEESE_TYPE_CAMEMBERT));
        assertTrue(CheeseType.isValidType(VALID_CHEESE_TYPE_BRIE));

        // Numbers only
        assertTrue(CheeseType.isValidType("10241982"));

        // Alphanumeric characters
        assertTrue(CheeseType.isValidType("Cheese Type 1"));

        // Long names
        assertTrue(CheeseType.isValidType("Aged Cashew &     Blue Green Algae Cheese"));
    }
}
