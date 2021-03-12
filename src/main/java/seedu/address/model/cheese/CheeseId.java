package seedu.address.model.cheese;

import seedu.address.model.AbstractId;

public class CheeseId extends AbstractId<CheeseId> {
    private static int nextId = 1;

    /**
     * Constructs a {@code CheeseId}.
     *
     * @param id A valid id.
     */
    public CheeseId(int id) {
        super(id);
        updateNextId(this);
    }

    public static CheeseId getNextId() {
        return new CheeseId(nextId);
    }

    private static void updateNextId(CheeseId otherId) {
        if (nextId <= otherId.value) {
            nextId = otherId.value + 1;
        }
    }
}
