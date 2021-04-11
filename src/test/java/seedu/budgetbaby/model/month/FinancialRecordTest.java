package seedu.budgetbaby.model.month;

import org.junit.jupiter.api.Test;
import seedu.budgetbaby.model.record.FinancialRecord;
import seedu.budgetbaby.testutil.FinancialRecordBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CAIFAN;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_AMOUNT_CAIFAN;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_TIMESTAMP_CAIFAN;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_TAG_SHOPPING;
import static seedu.budgetbaby.testutil.Assert.assertThrows;
import static seedu.budgetbaby.testutil.TypicalFinancialRecord.LUNCH;
import static seedu.budgetbaby.testutil.TypicalFinancialRecord.DINNER;

public class FinancialRecordTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        FinancialRecord record = new FinancialRecordBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> record.getCategories().remove(0));
    }

    @Test
    public void equals() {
        // same values -> returns true
        FinancialRecord lunchCopy = new FinancialRecordBuilder(LUNCH).build();
        assertTrue(LUNCH.equals(lunchCopy));

        // same object -> returns true
        assertTrue(LUNCH.equals(LUNCH));

        // null -> returns false
        assertFalse(LUNCH.equals(null));

        // different type -> returns false
        assertFalse(LUNCH.equals(5));

        // different person -> returns false
        assertFalse(LUNCH.equals(DINNER));

        // different description -> returns false
        FinancialRecord editedLunch = new FinancialRecordBuilder(LUNCH)
            .withDescription(VALID_DESCRIPTION_CAIFAN).build();
        assertFalse(LUNCH.equals(editedLunch));

        // different amount -> returns false
        editedLunch = new FinancialRecordBuilder(LUNCH)
            .withAmount(VALID_AMOUNT_CAIFAN).build();
        assertFalse(LUNCH.equals(editedLunch));

        // different timestamp -> returns false
        editedLunch = new FinancialRecordBuilder(LUNCH)
            .withTimestamp(VALID_TIMESTAMP_CAIFAN).build();
        assertFalse(LUNCH.equals(editedLunch));

        // different tags -> returns false
        editedLunch = new FinancialRecordBuilder(LUNCH)
            .withCategories(VALID_TAG_SHOPPING).build();
        assertFalse(LUNCH.equals(editedLunch));
    }
}