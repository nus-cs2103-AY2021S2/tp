package seedu.address.model;

import seedu.address.model.cheese.CheeseId;

public class CheeseIdStub extends CheeseId {
    /**
     * Constructs a {@code Id}.
     *
     * @param id A valid id.
     */
    private CheeseIdStub(int id) {
        super(id);
    }

    public static CheeseIdStub getNextId() {
        return getNextId(getNextIdValue());
    }

    public static CheeseIdStub getNextId(int id) {
        return new CheeseIdStub(id);
    }
}
