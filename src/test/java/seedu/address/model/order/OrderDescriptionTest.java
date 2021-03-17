package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OrderDescriptionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OrderDescription(null));
    }


    @Test
    public void constructor_invalidOrderDescription_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new OrderDescription(" "));
        assertThrows(IllegalArgumentException.class, () -> new OrderDescription(""));
    }

    @Test
    public void isValidOrderDescription() {
        // null order descriptions
        assertThrows(NullPointerException.class, () -> OrderDescription.isValidOrderDescription(null));

        // invalid order descriptions
        assertFalse(OrderDescription.isValidOrderDescription("")); // empty string
        assertFalse(OrderDescription.isValidOrderDescription(" ")); // spaces only

        // valid order descriptions
        assertTrue(OrderDescription.isValidOrderDescription("chocolate cake")); // alphabets only
        assertTrue(OrderDescription.isValidOrderDescription("12345")); // numbers only
        assertTrue(OrderDescription.isValidOrderDescription("2 x chocolate cake")); // alphanumeric characters
        assertTrue(OrderDescription.isValidOrderDescription("Chocolate Cake!")); // with capital letters and !
    }
}
