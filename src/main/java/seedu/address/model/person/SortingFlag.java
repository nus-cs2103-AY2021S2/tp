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

    public static final String MODULE_CODE_FLAG = "moduleCode";

    public static final String PRIORITY_TAG_FLAG = "priorityTag";

    public static final String MESSAGE_CONSTRAINTS = "Sorting flag should be one of either [dateTime],"
            + " [moduleCode] or [priorityTag] (case-sensitive).";

    public final SortingFlagType sortingFlagType;

    /**
     * Enumeration that represents the three possible types of sorting flags: {@code DATE_TIME, MODULE_CODE,
     * PRIORITY_TAG}. <br>
     * Each type has a string label attached to it.
     */
    public enum SortingFlagType {
        DATE_TIME (DATE_TIME_FLAG),
        MODULE_CODE (MODULE_CODE_FLAG),
        PRIORITY_TAG (PRIORITY_TAG_FLAG);

        private final String sortingFlag;

        SortingFlagType(String sortingFlag) {
            this.sortingFlag = sortingFlag;
        }

        public String getSortingFlag() {
            return sortingFlag;
        }
    }

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
        case MODULE_CODE_FLAG:
            sortingFlagType = SortingFlagType.MODULE_CODE;
            break;
        case PRIORITY_TAG_FLAG:
            sortingFlagType = SortingFlagType.PRIORITY_TAG;
            break;
        default:
            throw new IllegalArgumentException(SortingFlag.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Checks if a given string represents a valid sorting flag type.
     */
    public static boolean isValidSortingTypeFlag(String s) {
        return s.equals(DATE_TIME_FLAG) || s.equals(MODULE_CODE_FLAG) || s.equals(PRIORITY_TAG_FLAG);
    }

    @Override
    public String toString() {
        return String.format("[%s]", sortingFlagType.getSortingFlag());
    }
}


