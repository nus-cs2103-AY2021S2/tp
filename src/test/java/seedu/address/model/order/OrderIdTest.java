package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OrderIdTest {
    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        int invalidId = -1;
        assertThrows(IllegalArgumentException.class, () -> new OrderId(invalidId));
    }

    @Test
    public void getNextId_autoincrement_success() {
        OrderId orderId = OrderId.getNextId();
        OrderId orderId2 = OrderId.getNextId();
        assertEquals(orderId.value + 1, orderId2.value);
    }

    @Test
    public void getNextId_maxAutoincrement_success() {
        // Creates an order id with the given large value
        final int largeValue = 1000;
        OrderId.getNextId(largeValue);

        OrderId orderId2 = OrderId.getNextId();
        assertEquals(largeValue + 1, orderId2.value);
    }
}
