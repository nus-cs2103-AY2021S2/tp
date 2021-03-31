package seedu.address.testutil;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Indexes.
 */
public class IndexesUtil {

    /**
     * Returns the part of command string for the given {@code indexes}'s details.
     */
    public static String getIndexesDetails(List<Index> indexes) {
        StringBuilder builder = new StringBuilder();
        for (Index index : indexes) {
            builder.append(index.getZeroBased() + " ");
        }
        return builder.toString().stripTrailing();
    }

}
