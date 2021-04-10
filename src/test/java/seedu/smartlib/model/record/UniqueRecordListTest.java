package seedu.smartlib.model.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.testutil.Assert.assertThrows;
import static seedu.smartlib.testutil.TypicalModels.RECORD_A;
import static seedu.smartlib.testutil.TypicalModels.RECORD_B;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.smartlib.model.record.exceptions.DuplicateRecordException;
import seedu.smartlib.model.record.exceptions.RecordNotFoundException;

public class UniqueRecordListTest {

    @Test
    public void contains_nullRecord_throwsNullPointerException() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        assertThrows(NullPointerException.class, () -> uniqueRecordList.contains(null));
    }

    @Test
    public void contains_recordNotInList_returnsFalse() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        assertFalse(uniqueRecordList.contains(RECORD_A));
    }

    @Test
    public void contains_recordInList_returnsTrue() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        uniqueRecordList.addRecord(RECORD_A);
        assertTrue(uniqueRecordList.contains(RECORD_A));
    }

    @Test
    public void contains_recordWithSameIdentityFieldsInList_returnsTrue() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        uniqueRecordList.addRecord(RECORD_A);
        Record editedRecord = new Record(
                RECORD_A.getBookName(),
                RECORD_A.getBookBarcode(),
                RECORD_A.getReaderName(),
                RECORD_A.getDateBorrowed()
        );
        assertTrue(uniqueRecordList.contains(editedRecord));
    }

    @Test
    public void addRecord_nullRecord_throwsNullPointerException() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        assertThrows(NullPointerException.class, () -> uniqueRecordList.addRecord(null));
    }

    @Test
    public void addRecord_duplicateRecord_throwsDuplicateRecordException() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        uniqueRecordList.addRecord(RECORD_A);
        assertThrows(DuplicateRecordException.class, () -> uniqueRecordList.addRecord(RECORD_A));
    }

    @Test
    public void setRecord_nullTargetRecord_throwsNullPointerException() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        assertThrows(NullPointerException.class, () -> uniqueRecordList.setRecord(null, RECORD_A));
    }

    @Test
    public void setRecord_nullEditedRecord_throwsNullPointerException() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        assertThrows(NullPointerException.class, () -> uniqueRecordList.setRecord(RECORD_A, null));
    }

    @Test
    public void setRecord_targetRecordNotInList_throwsRecordNotFoundException() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        assertThrows(RecordNotFoundException.class, () -> uniqueRecordList.setRecord(RECORD_A, RECORD_A));
    }

    @Test
    public void setRecord_editedRecordIsSameRecord_success() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        uniqueRecordList.addRecord(RECORD_A);
        uniqueRecordList.setRecord(RECORD_A, RECORD_A);

        UniqueRecordList expectedUniqueRecordList = new UniqueRecordList();
        expectedUniqueRecordList.addRecord(RECORD_A);
        assertEquals(expectedUniqueRecordList, uniqueRecordList);;
    }

    @Test
    public void setRecord_editedRecordHasSameIdentity_success() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        uniqueRecordList.addRecord(RECORD_A);
        Record editedRecord = new Record(
                RECORD_A.getBookName(),
                RECORD_A.getBookBarcode(),
                RECORD_A.getReaderName(),
                RECORD_A.getDateBorrowed()
        );
        uniqueRecordList.setRecord(RECORD_A, editedRecord);

        UniqueRecordList expectedUniqueRecordList = new UniqueRecordList();
        expectedUniqueRecordList.addRecord(editedRecord);
        assertEquals(expectedUniqueRecordList, uniqueRecordList);
    }

    @Test
    public void setRecord_editedRecordHasDifferentIdentity_success() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        uniqueRecordList.addRecord(RECORD_A);
        uniqueRecordList.setRecord(RECORD_A, RECORD_B);

        UniqueRecordList expectedUniqueRecordList = new UniqueRecordList();
        expectedUniqueRecordList.addRecord(RECORD_B);
        assertEquals(expectedUniqueRecordList, uniqueRecordList);
    }

    @Test
    public void setRecord_editedRecordHasNonUniqueIdentity_throwsDuplicateRecordException() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        uniqueRecordList.addRecord(RECORD_A);
        uniqueRecordList.addRecord(RECORD_B);
        assertThrows(DuplicateRecordException.class, () -> uniqueRecordList.setRecord(RECORD_A, RECORD_B));
    }

    @Test
    public void remove_nullRecord_throwsNullPointerException() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        assertThrows(NullPointerException.class, () -> uniqueRecordList.remove(null));
    }

    @Test
    public void remove_recordDoesNotExist_throwsRecordNotFoundException() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        assertThrows(RecordNotFoundException.class, () -> uniqueRecordList.remove(RECORD_A));
    }

    @Test
    public void remove_existingRecord_removesRecord() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        uniqueRecordList.addRecord(RECORD_A);
        uniqueRecordList.remove(RECORD_A);

        UniqueRecordList expectedUniqueRecordList = new UniqueRecordList();
        assertEquals(expectedUniqueRecordList, uniqueRecordList);
    }

    @Test
    public void setRecords_nullUniqueRecordList_throwsNullPointerException() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        assertThrows(NullPointerException.class, () -> uniqueRecordList.setRecords((UniqueRecordList) null));
    }

    @Test
    public void setRecords_uniqueRecordList_replacesOwnListWithProvidedUniqueRecordList() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        uniqueRecordList.addRecord(RECORD_A);
        UniqueRecordList expectedUniqueRecordList = new UniqueRecordList();
        expectedUniqueRecordList.addRecord(RECORD_B);

        uniqueRecordList.setRecords(expectedUniqueRecordList);
        assertEquals(expectedUniqueRecordList, uniqueRecordList);
    }

    @Test
    public void setRecords_nullList_throwsNullPointerException() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        assertThrows(NullPointerException.class, () -> uniqueRecordList.setRecords((List<Record>) null));
    }

    @Test
    public void setRecords_list_replacesOwnListWithProvidedList() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        uniqueRecordList.addRecord(RECORD_A);
        List<Record> recordList = Collections.singletonList(RECORD_B);
        uniqueRecordList.setRecords(recordList);

        UniqueRecordList expectedUniqueRecordList = new UniqueRecordList();
        expectedUniqueRecordList.addRecord(RECORD_B);
        assertEquals(expectedUniqueRecordList, uniqueRecordList);
    }

    @Test
    public void setRecords_listWithDuplicateRecords_throwsDuplicateRecordException() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        List<Record> listWithDuplicateRecords = Arrays.asList(RECORD_A, RECORD_A);
        assertThrows(DuplicateRecordException.class, () -> uniqueRecordList.setRecords(listWithDuplicateRecords));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueRecordList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void iterator() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        uniqueRecordList.addRecord(RECORD_A);

        assertEquals(uniqueRecordList.iterator().next(), RECORD_A); // iterator works
    }

    @Test
    public void equals() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        uniqueRecordList.addRecord(RECORD_A);
        UniqueRecordList uniqueRecordListCopy = new UniqueRecordList();
        uniqueRecordListCopy.addRecord(RECORD_A);
        UniqueRecordList uniqueRecordList2 = new UniqueRecordList();
        uniqueRecordList2.addRecord(RECORD_B);

        // null -> returns false
        assertFalse(uniqueRecordList.equals(null));

        // different types -> returns false
        assertFalse(uniqueRecordList.equals(0.5f));
        assertFalse(uniqueRecordList.equals(" "));

        // same object -> returns true
        assertTrue(uniqueRecordList.equals(uniqueRecordList));

        // same records -> returns true
        assertTrue(uniqueRecordList.equals(uniqueRecordListCopy));

        // different values -> returns false
        assertFalse(uniqueRecordList.equals(uniqueRecordList2));
    }

    @Test
    public void hashcode() {
        UniqueRecordList uniqueRecordList = new UniqueRecordList();
        uniqueRecordList.addRecord(RECORD_A);
        UniqueRecordList uniqueRecordListCopy = new UniqueRecordList();
        uniqueRecordListCopy.addRecord(RECORD_A);
        UniqueRecordList uniqueRecordList2 = new UniqueRecordList();
        uniqueRecordList2.addRecord(RECORD_B);

        // same object -> returns same hashcode
        assertEquals(uniqueRecordList.hashCode(), uniqueRecordList.hashCode());

        // same records -> returns same hashcode
        assertEquals(uniqueRecordList.hashCode(), uniqueRecordListCopy.hashCode());

        // different records -> returns different hashcode
        assertNotEquals(uniqueRecordList.hashCode(), uniqueRecordList2.hashCode());
    }

}
