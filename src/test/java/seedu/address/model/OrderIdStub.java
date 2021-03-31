package seedu.address.model;

import seedu.address.model.order.OrderId;

public class OrderIdStub extends OrderId {
    /**
     * Constructs a {@code Id}.
     *
     * @param id A valid id.
     */
    private OrderIdStub(int id) {
        super(id);
    }

    public static OrderIdStub getNextId() {
        return getNextId(getNextIdValue());
    }

    public static OrderIdStub getNextId(int id) {
        return new OrderIdStub(id);
    }
}
