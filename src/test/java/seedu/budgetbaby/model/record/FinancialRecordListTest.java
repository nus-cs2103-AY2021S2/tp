package seedu.budgetbaby.model.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.budgetbaby.testutil.Assert.assertThrows;
import static seedu.budgetbaby.testutil.TypicalFinancialRecord.DINNER;
import static seedu.budgetbaby.testutil.TypicalFinancialRecord.LUNCH;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.budgetbaby.model.record.exception.FinancialRecordNotFoundException;

public class FinancialRecordListTest {

    private final FinancialRecordList financialRecordList = new FinancialRecordList();

    @Test
    public void add_nullFr_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> financialRecordList.add(null));
    }

    @Test
    public void setPerson_nullFr_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> financialRecordList.setFinancialRecord(null, LUNCH));
    }

    @Test
    public void setPerson_nullEditedFr_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> financialRecordList
            .setFinancialRecord(LUNCH, null));
    }

    @Test
    public void remove_nullFr_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> financialRecordList.remove(null));
    }

    @Test
    public void remove_frDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(FinancialRecordNotFoundException.class, () -> financialRecordList.remove(LUNCH));
    }

    @Test
    public void remove_existingFr_removeFr() {
        financialRecordList.add(LUNCH);
        financialRecordList.remove(LUNCH);
        FinancialRecordList expectedList = new FinancialRecordList();
        assertEquals(expectedList, financialRecordList);
    }

    @Test
    public void setFrs_nullFrList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> financialRecordList.setFinancialRecords((FinancialRecordList) null));
    }

    @Test
    public void setFrs_frList_replacesOwnListWithProvidedFrList() {
        financialRecordList.add(LUNCH);
        FinancialRecordList expectedList = new FinancialRecordList();
        expectedList.add(DINNER);
        financialRecordList.setFinancialRecords(expectedList);
        assertEquals(expectedList, financialRecordList);
    }

    @Test
    public void setFrs_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> financialRecordList
            .setFinancialRecords((List<FinancialRecord>) null));
    }

    @Test
    public void setFrs_list_replacesOwnListWithProvidedList() {
        financialRecordList.add(LUNCH);
        List<FinancialRecord> frList = Collections.singletonList(DINNER);
        financialRecordList.setFinancialRecords(frList);
        FinancialRecordList expectedList = new FinancialRecordList();
        expectedList.add(DINNER);
        assertEquals(expectedList, financialRecordList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> financialRecordList.asUnmodifiableObservableList().remove(0));
    }
}




