package seedu.storemando.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_QUANTITY_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_QUANTITY_CHEESE;
import static seedu.storemando.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class QuantityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Quantity(null));
    }

    @Test
    public void constructor_invalidQuantity_throwsIllegalArgumentException() {
        String invalidQuantity = "";
        assertThrows(IllegalArgumentException.class, () -> new Quantity(invalidQuantity));
    }

    @Test
    public void isValidQuantity() {
        // null quantity number
        assertThrows(NullPointerException.class, () -> Quantity.isValidQuantity(null));

        // invalid quantity numbers
        assertFalse(Quantity.isValidQuantity("")); // empty string
        assertFalse(Quantity.isValidQuantity(" ")); // spaces only
        assertFalse(Quantity.isValidQuantity("quantity")); // non-numeric
        assertFalse(Quantity.isValidQuantity("9011p041")); // alphabets within digits
        assertFalse(Quantity.isValidQuantity("9312 1534")); // spaces within digits

        // valid quantity numbers
        assertTrue(Quantity.isValidQuantity("9")); // exactly 1 number
        assertTrue(Quantity.isValidQuantity("93121534"));
        assertTrue(Quantity.isValidQuantity("124293842033123")); // long quantity numbers
    }

    @Test
    public void compare_quantities_success() {
        assertTrue(new ItemName(VALID_QUANTITY_CHEESE).compare(new ItemName(VALID_QUANTITY_BANANA)) < 0);
        assertTrue(new ItemName(VALID_QUANTITY_BANANA).compare(new ItemName(VALID_QUANTITY_CHEESE)) > 0);
        assertTrue(new ItemName(VALID_QUANTITY_BANANA).compare(new ItemName(VALID_QUANTITY_BANANA)) == 0);
    }
}
