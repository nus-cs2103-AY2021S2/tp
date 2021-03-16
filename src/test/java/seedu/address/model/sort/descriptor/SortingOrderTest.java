package seedu.address.model.sort.descriptor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SortingOrderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortingOrder(null));
    }

    @Test
    public void constructor_invalidSortingOrder_throwsIllegalArgumentException() {
        String invalidSortingOrder = "";
        assertThrows(IllegalArgumentException.class, () -> new SortingOrder(invalidSortingOrder));
    }

    @Test
    public void isValidSortingOrder() {
        // null sortingOrder
        assertThrows(NullPointerException.class, () -> SortingOrder.isValidSortingOrder(null));

        // invalid sortingOrder
        assertFalse(SortingOrder.isValidSortingOrder("")); // empty string
        assertFalse(SortingOrder.isValidSortingOrder(" ")); // spaces only

        // valid sortingOrder
        assertTrue(SortingOrder.isValidSortingOrder("asc"));
        assertTrue(SortingOrder.isValidSortingOrder("des")); // one character
    }
}
