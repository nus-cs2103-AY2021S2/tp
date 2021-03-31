package seedu.address.model.cheese;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CheeseIdTest {
    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        int invalidId = -1;
        assertThrows(IllegalArgumentException.class, () -> new CheeseId(invalidId));
    }

    @Test
    public void getNextId_autoincrement_success() {
        CheeseId cheeseId = CheeseId.getNextId();
        CheeseId cheeseId2 = CheeseId.getNextId();
        assertEquals(cheeseId.value + 1, cheeseId2.value);
    }

    @Test
    public void getNextId_maxAutoincrement_success() {
        // Creates a cheese id with the given large value
        final int largeValue = 1000;
        CheeseId.getNextId(largeValue);

        CheeseId orderId2 = CheeseId.getNextId();
        assertEquals(largeValue + 1, orderId2.value);
    }
}
