package seedu.address.model.cheese;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_BRIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_FETA;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CheeseTypeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> CheeseType.getCheeseType(null));
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
}
