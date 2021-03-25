package seedu.address.model.person;

/**
 * Enumeration that represents the three possible types of sorting flags: {@code DATE_TIME, MODULE_CODE,
 * PRIORITY_TAG}. <br>
 * Each type has a string label attached to it.
 */
public enum SortingFlagType {
    DATE_TIME (SortingFlag.DATE_TIME_FLAG),
    MODULE_CODE (SortingFlag.MODULE_CODE_FLAG),
    PRIORITY_TAG (SortingFlag.PRIORITY_TAG_FLAG);

    private final String sortingFlag;

    SortingFlagType(String sortingFlag) {
        this.sortingFlag = sortingFlag;
    }

    public String getSortingFlag() {
        return sortingFlag;
    }
}
