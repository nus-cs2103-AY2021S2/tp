package seedu.budgetbaby.model.month;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.budgetbaby.testutil.Assert.assertThrows;
import static seedu.budgetbaby.testutil.TypicalFinancialRecord.FEB;
import static seedu.budgetbaby.testutil.TypicalFinancialRecord.JAN;

import org.junit.jupiter.api.Test;

import seedu.budgetbaby.testutil.MonthBuilder;

public class MonthTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Month month = new MonthBuilder().build();
        assertThrows(UnsupportedOperationException.class, ()
            -> month.getFinancialRecordList().remove(0));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Month monthCopy = new MonthBuilder(JAN).build();
        assertTrue(JAN.equals(monthCopy));

        // same object -> returns true
        assertTrue(JAN.equals(JAN));

        // null -> returns false
        assertFalse(JAN.equals(null));

        // different type -> returns false
        assertFalse(JAN.equals(5));

        // different person -> returns false
        assertFalse(JAN.equals(FEB));

        // different description -> returns false
        Month editedJan = new MonthBuilder(JAN)
            .withBudget(20.0).build();
        assertFalse(JAN.equals(editedJan));
    }
}
