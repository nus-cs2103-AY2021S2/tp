package chim.model.customer;

import static chim.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chim.model.CustomerIdStub;

public class CustomerIdTest {
    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        int invalidId = -1;
        assertThrows(IllegalArgumentException.class, () -> CustomerId.getNextId(invalidId));
    }

    @Test
    public void getNextId_autoincrement_success() {
        CustomerId customerId = CustomerId.getNextId();
        CustomerId customerId2 = CustomerId.getNextId();
        assertEquals(customerId.value + 1, customerId2.value);
    }

    @Test
    public void getNextId_maxAutoincrement_success() {
        // Creates an customer id with the given large value
        final int largeValue = 1000;
        CustomerId.getNextId(largeValue);

        CustomerId nextCustomerId = CustomerId.getNextId();
        assertEquals(largeValue + 1, nextCustomerId.value);
    }

    // Ensures that Customer stub is working as intended
    @Test
    public void getNextIdStub_maxNoAutoincrement_success() {
        // Creates an customer stub with the given large value
        final int largeValue = CustomerIdStub.getNextIdValue() + 10;
        CustomerId.getNextId(largeValue);

        CustomerId nextCustomerStubId = CustomerIdStub.getNextId();
        assertEquals(largeValue + 1, nextCustomerStubId.value);
    }
}
