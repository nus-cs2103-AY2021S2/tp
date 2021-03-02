package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class QuantityTest {
    @Test
    public void constructor_invalidQuantity_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity(-1));
    }

    @Test
    public void isValidQuantity() {
        assertFalse(Quantity.isValidQuantity(-1));
        assertFalse(Quantity.isValidQuantity(0));
        assertTrue(Quantity.isValidQuantity(138));
    }
}
