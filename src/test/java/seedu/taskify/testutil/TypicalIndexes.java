package seedu.taskify.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.taskify.commons.core.index.Index;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_TASK = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_TASK = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_TASK = Index.fromOneBased(3);
    public static final List<Index> INDEXES_FIRST_TO_THIRD_TASK;
    static {
        INDEXES_FIRST_TO_THIRD_TASK = new ArrayList<>();
        INDEXES_FIRST_TO_THIRD_TASK.add(INDEX_FIRST_TASK);
        INDEXES_FIRST_TO_THIRD_TASK.add(INDEX_SECOND_TASK);
        INDEXES_FIRST_TO_THIRD_TASK.add(INDEX_THIRD_TASK);
    }
}
