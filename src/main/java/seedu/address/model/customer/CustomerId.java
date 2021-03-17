package seedu.address.model.customer;

import seedu.address.model.AbstractId;

public class CustomerId extends AbstractId<CustomerId> {
    private static int nextId = 1;

    /**
     * Constructs a {@code CustomerId}.
     *
     * @param id A valid id.
     */
    protected CustomerId(int id) {
        super(id);
    }

    public static CustomerId getNextId() {
        return getNextId(nextId);
    }


    public static CustomerId getNextId(int id) {
        CustomerId result = new CustomerId(id);
        updateNextId(result);
        return result;
    }


    private static void updateNextId(CustomerId otherId) {
        if (nextId <= otherId.value) {
            // Ensures that the tests will produce customer ids of the same value
            nextId = otherId.value + 1;
        }
    }

    protected static int getNextIdValue() {
        return nextId;
    }
}
