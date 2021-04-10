package chim.model.order;

import static chim.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class QuantityTest {
    @Test
    public void constructor_invalidQuantity_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity(-1));
    }

    @Test
    public void isValidQuantity() {
        // Negative Quantity
        assertFalse(Quantity.isValidQuantity(-1));

        // Zero Quantity
        assertFalse(Quantity.isValidQuantity(0));

        // Positive Quantity
        assertTrue(Quantity.isValidQuantity(138));
    }
}
