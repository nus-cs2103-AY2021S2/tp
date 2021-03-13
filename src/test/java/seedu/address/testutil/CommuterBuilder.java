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


    private final Index index1;
    private final Index index2;

    /**
     * Creates a {@code DriverBuilder} with the default details.
     */
    public CommuterBuilder() {
        index1 = Index.fromOneBased(DEFAULT_INDEX_1_AS_INT);
        index2 = Index.fromOneBased(DEFAULT_INDEX_2_AS_INT);
    }

    /**
     * Builds the commuter set with the given details.
     * @return A {@code Set} containing the given {@code Index}s
     */
    public Set<Index> build() {
        final Set<Index> indicesSet = new HashSet<>();
        indicesSet.add(index1);
        indicesSet.add(index2);
        return indicesSet;
    }
}
