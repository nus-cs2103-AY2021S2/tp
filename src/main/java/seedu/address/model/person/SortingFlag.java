package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Flag to indicate which type of sort is to be performed. There are three types of flag indicators: <br>
 * 1. Date & time
 * 2. Module code
 * 3. Priority tag
 */
public class SortingFlag {

    public static final String DATE_TIME_FLAG = "dateTime";

    public static final String TASK_NAME_FLAG = "taskName";

    public static final String MODULE_CODE_FLAG = "moduleCode";

    public static final String PRIORITY_TAG_FLAG = "priorityTag";

    public static final String WEIGHTAGE_FLAG = "weightage";

    public static final String MESSAGE_CONSTRAINTS = "Sorting flag should be one of either [dateTime],"
            + "[taskName], [moduleCode], [priorityTag] or [weightage] (case-sensitive).";

    public final SortingFlagType sortingFlagType;

    /**
     * Constructs a SortingFlag with the appropriate given type.
     * @throws IllegalArgumentException if the given type is invalid (i.e. does not belong to either one of the three
     * types).
     */
    public SortingFlag(String sortingFlag) {
        requireNonNull(sortingFlag);
        checkArgument(isValidSortingTypeFlag(sortingFlag), SortingFlag.MESSAGE_CONSTRAINTS);
        switch (sortingFlag) {
        case DATE_TIME_FLAG:
            sortingFlagType = SortingFlagType.DATE_TIME;
            break;
        case TASK_NAME_FLAG:
            sortingFlagType = SortingFlagType.TASK_NAME;
            break;
        case MODULE_CODE_FLAG:
            sortingFlagType = SortingFlagType.MODULE_CODE;
            break;
        case PRIORITY_TAG_FLAG:
            sortingFlagType = SortingFlagType.PRIORITY_TAG;
            break;
        case WEIGHTAGE_FLAG:
            sortingFlagType = SortingFlagType.WEIGHTAGE;
            break;
        default:
            throw new IllegalArgumentException(SortingFlag.MESSAGE_CONSTRAINTS);
        }
    }

    public SortingFlagType getSortingFlagType() {
        return sortingFlagType;
    }

    /**
     * Checks if a given string represents a valid sorting flag type.
     */
    public static boolean isValidSortingTypeFlag(String s) {
        return s.equals(DATE_TIME_FLAG)
                || s.equals(TASK_NAME_FLAG)
                || s.equals(MODULE_CODE_FLAG)
                || s.equals(PRIORITY_TAG_FLAG)
                || s.equals(WEIGHTAGE_FLAG);
    }

    @Override
    public String toString() {
        return String.format("[%s]", sortingFlagType.getSortingFlag());
    }
}


