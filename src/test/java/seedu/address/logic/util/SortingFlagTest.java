package seedu.address.logic.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SortingFlagTest {

    private SortingFlag validDateTimeSortingFlag = new SortingFlag(SortingFlag.DATE_TIME_FLAG);

    private SortingFlag validModuleCodeSortingFlag = new SortingFlag(SortingFlag.MODULE_CODE_FLAG);

    private SortingFlag validPriorityTagSortingFlag = new SortingFlag(SortingFlag.PRIORITY_TAG_FLAG);

    private SortingFlag validTaskNameSortingFlag = new SortingFlag(SortingFlag.TASK_NAME_FLAG);

    private SortingFlag validWeightageSortingFlag = new SortingFlag(SortingFlag.WEIGHTAGE_FLAG);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortingFlag(null));
    }

    @Test
    public void constructor_invalidSortingFlag_throwsIllegalArgumentException() {
        String invalidSortingFlag = "";
        assertThrows(IllegalArgumentException.class, () -> new SortingFlag(invalidSortingFlag));
    }

    @Test
    public void constructor_validDateTimeSortingFlag_success() {
        assertEquals(new SortingFlag("dateTime"), validDateTimeSortingFlag);
    }

    @Test
    public void constructor_validModuleCodeSortingFlag_success() {
        assertEquals(new SortingFlag("moduleCode"), validModuleCodeSortingFlag);
    }

    @Test
    public void constructor_validPriorityTagSortingFlag_success() {
        assertEquals(new SortingFlag("priorityTag"), validPriorityTagSortingFlag);
    }

    @Test
    public void constructor_validTaskNameSortingFlag_success() {
        assertEquals(new SortingFlag("taskName"), validTaskNameSortingFlag);
    }

    @Test
    public void constructor_validWeightageSortingFlag_success() {
        assertEquals(new SortingFlag("weightage"), validWeightageSortingFlag);
    }

    @Test
    public void equals() {

        // same object -> returns true
        assertTrue(validDateTimeSortingFlag.equals(validDateTimeSortingFlag));
        assertTrue(validModuleCodeSortingFlag.equals(validModuleCodeSortingFlag));
        assertTrue(validPriorityTagSortingFlag.equals(validPriorityTagSortingFlag));
        assertTrue(validTaskNameSortingFlag.equals(validTaskNameSortingFlag));
        assertTrue(validWeightageSortingFlag.equals(validWeightageSortingFlag));

        // same value -> returns true
        assertTrue(validDateTimeSortingFlag.equals(new SortingFlag(SortingFlag.DATE_TIME_FLAG)));
        assertTrue(validModuleCodeSortingFlag.equals(new SortingFlag(SortingFlag.MODULE_CODE_FLAG)));
        assertTrue(validPriorityTagSortingFlag.equals(new SortingFlag(SortingFlag.PRIORITY_TAG_FLAG)));
        assertTrue(validTaskNameSortingFlag.equals(new SortingFlag(SortingFlag.TASK_NAME_FLAG)));
        assertTrue(validWeightageSortingFlag.equals(new SortingFlag(SortingFlag.WEIGHTAGE_FLAG)));

        // diff type -> returns false
        assertFalse(validDateTimeSortingFlag.equals(1));
        assertFalse(validDateTimeSortingFlag.equals(""));

        // null -> returns false
        assertFalse(validDateTimeSortingFlag.equals(null));

        // diff values -> returns false
        assertFalse(validDateTimeSortingFlag.equals(validModuleCodeSortingFlag));
        assertFalse(validDateTimeSortingFlag.equals(validPriorityTagSortingFlag));
        assertFalse(validDateTimeSortingFlag.equals(validTaskNameSortingFlag));
        assertFalse(validDateTimeSortingFlag.equals(validWeightageSortingFlag));

    }

    @Test
    public void isValidSortingType() {
        // valid types
        assertTrue(SortingFlag.isValidSortingType(SortingFlag.DATE_TIME_FLAG));
        assertTrue(SortingFlag.isValidSortingType(SortingFlag.MODULE_CODE_FLAG));
        assertTrue(SortingFlag.isValidSortingType(SortingFlag.PRIORITY_TAG_FLAG));
        assertTrue(SortingFlag.isValidSortingType(SortingFlag.TASK_NAME_FLAG));
        assertTrue(SortingFlag.isValidSortingType(SortingFlag.WEIGHTAGE_FLAG));

        // invalid types
        assertFalse(SortingFlag.isValidSortingType(""));
    }
}
