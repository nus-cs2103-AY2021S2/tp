package seedu.address.model;


import org.junit.jupiter.api.Test;

import java.util.Collections;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDates.JURONG_SEC2_EXAM;
import static seedu.address.testutil.TypicalDates.getTypicalDatesBook;



public class DatesBookTest {

    private final DatesBook datesBook = new DatesBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), datesBook.getImportantDatesList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> datesBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyDatesBook_replacesData() {
        DatesBook newData = getTypicalDatesBook();
        datesBook.resetData(newData);
        assertEquals(newData, datesBook);
    }

    @Test
    public void hasImportantDate_nullImportantDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> datesBook.hasImportantDate(null));
    }

    @Test
    public void hasImportantDate_importantDateNotInDatesBook_returnsFalse() {
        assertFalse(datesBook.hasImportantDate(JURONG_SEC2_EXAM));
    }

    @Test
    public void hasImportantDate_importantDateInDatesBook_returnsTrue() {
        datesBook.addImportantDate(JURONG_SEC2_EXAM);
        assertTrue(datesBook.hasImportantDate(JURONG_SEC2_EXAM));
    }

    @Test
    public void getImportantDatesList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> datesBook.getImportantDatesList().remove(0));
    }
}
