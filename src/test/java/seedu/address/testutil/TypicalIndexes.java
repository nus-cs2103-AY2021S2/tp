package seedu.address.testutil;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {

    public static final Index INDEX_FIRST_PERSON = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_PERSON = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_PERSON = Index.fromOneBased(3);
    public static final List<Index> VALID_INDEXES = Arrays
            .asList(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON, INDEX_THIRD_PERSON);
    public static final String NEGATIVE_INDEX_STRING = "-5";
    public static final String OUT_OF_RANGE_INDEX_STRING = Long.toString(Integer.MAX_VALUE + 1);
    public static final String ZERO_INDEX_STRING = "0";
    public static final String INVALID_INDEX_STRING = "1 some random string";
    public static final String VALID_INDEX_STRING = "1";
}
