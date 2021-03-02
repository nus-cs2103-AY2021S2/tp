package seedu.address.model.cheese;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CheeseTypeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> CheeseType.getCheeseType(null));
    }

    @Test
    public void constructor_validCheeseName_throwsIllegalArgumentException() {
        CheeseType brieCheeseType = CheeseType.getCheeseType("Brie");
        assertEquals(brieCheeseType.value, "Brie");
    }

    @Test
    public void constructor_verifySameCheeseType_throwsIllegalArgumentException() {
        CheeseType fetaCheeseType = CheeseType.getCheeseType("Feta");
        assertEquals(fetaCheeseType.value, "Feta");
        String newFetaString = new String("Feta");
        CheeseType fetaCheeseType2 = CheeseType.getCheeseType(newFetaString);
        assertTrue(fetaCheeseType == fetaCheeseType2);
    }
}
