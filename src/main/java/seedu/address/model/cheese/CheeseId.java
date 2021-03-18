package seedu.address.model.cheese;

import seedu.address.model.AbstractId;

public class CheeseId extends AbstractId<CheeseId> {
    private static int nextId = 1;

    /**
     * Constructs a {@code CheeseId}.
     *
     * @param id A valid id.
     */
    protected CheeseId(int id) {
        super(id);
    }

    public static CheeseId getNextId() {
        return getNextId(nextId);
    }


    public static CheeseId getNextId(int id) {
        CheeseId result = new CheeseId(id);
        updateNextId(result);
        return result;
    }


    private static void updateNextId(CheeseId otherId) {
        if (nextId <= otherId.value) {
            nextId = otherId.value + 1;
        }
    }

    protected static int getNextIdValue() {
        return nextId;
    }
}
