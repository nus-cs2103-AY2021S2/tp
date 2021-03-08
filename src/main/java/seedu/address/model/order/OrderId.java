package seedu.address.model.order;

import seedu.address.model.AbstractId;

public class OrderId extends AbstractId<OrderId> {
    private static int nextId = 1;

    /**
     * Constructs a {@code OrderId}.
     *
     * @param id A valid id.
     */
    public OrderId(int id) {
        super(id);
        updateNextId(this);
    }

    public static OrderId getNextId() {
        return new OrderId(nextId);
    }

    private static void updateNextId(OrderId otherId) {
        if (nextId <= otherId.value) {
            nextId = otherId.value + 1;
        }
    }
}
