package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;

/**
 * A utility class to help with building Commuter sets.
 */
public class CommuterBuilder {
    private static final String DEFAULT_INDEX_1 = "1";
    private static final String DEFAULT_INDEX_2 = "2";

    private static final int DEFAULT_INDEX_1_AS_INT = Integer.parseInt(DEFAULT_INDEX_1);
    private static final int DEFAULT_INDEX_2_AS_INT = Integer.parseInt(DEFAULT_INDEX_2);

    private Set<Index> indices;

    /**
     * Creates a {@code DriverBuilder} with the default details.
     */
    public CommuterBuilder() {
        indices = new HashSet<>();
        indices.add(Index.fromOneBased(DEFAULT_INDEX_1_AS_INT));
        indices.add(Index.fromOneBased(DEFAULT_INDEX_2_AS_INT));
    }

    /**
     * Sets the {@code Index}es of the {@code Set} that we are building.
     */
    public CommuterBuilder withIndices(int[] intIndices) {
        this.indices.clear();
        for (int idx : intIndices) {
            indices.add(Index.fromOneBased(idx));
        }
        return this;
    }

    /**
     * Builds the commuter set with the given details.
     * @return A {@code Set} containing the given {@code Index}s
     */
    public Set<Index> build() {
        return indices;
    }
}
