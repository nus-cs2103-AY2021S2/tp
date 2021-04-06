package seedu.budgetbaby.testutil;

import seedu.budgetbaby.commons.core.index.Index;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_PERSON = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_PERSON = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_PERSON = Index.fromOneBased(3);
    public static final Index INDEX_FOURTH_PERSON = Index.fromOneBased(4);
    public static final Index INDEX_FIFTH_PERSON = Index.fromOneBased(5);

    public static final List<Index> INDEX_FIRST_FR = new ArrayList<>(Arrays.asList(Index.fromOneBased(1)));
    public static final List<Index> INDEX_SECOND_FR = new ArrayList<>(Arrays.asList(Index.fromOneBased(2)));
    public static final List<Index> INDEX_THIRD_FR = new ArrayList<>(Arrays.asList(Index.fromOneBased(3)));
    public static final List<Index> INDEX_FOURTH_FR = new ArrayList<>(Arrays.asList(Index.fromOneBased(4)));
    public static final List<Index> INDEX_FIFTH_FR = new ArrayList<>(Arrays.asList(Index.fromOneBased(5)));


}
