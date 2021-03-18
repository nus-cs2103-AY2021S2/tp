package seedu.address.model.order;

import seedu.address.model.AbstractId;

public class OrderId extends AbstractId<OrderId> {
    private static int nextId = 1;

    /**
     * Constructs a {@code OrderId}.
     *
     * @param id A valid id.
     */
    protected OrderId(int id) {
        super(id);
    }

    public static OrderId getNextId() {
        return getNextId(nextId);
    }

    public static OrderId getNextId(int id) {
        OrderId result = new OrderId(id);
        updateNextId(result);
        return result;
    }

    private static void updateNextId(OrderId otherId) {
        if (nextId <= otherId.value) {
            nextId = otherId.value + 1;
        }
    }

    protected static int getNextIdValue() {
        return nextId;
    }
}
