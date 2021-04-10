package seedu.smartlib.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_NAME_MAZE;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_TAG_VIP;
import static seedu.smartlib.testutil.Assert.assertThrows;
import static seedu.smartlib.testutil.TypicalModels.ALICE;
import static seedu.smartlib.testutil.TypicalModels.BENSON;
import static seedu.smartlib.testutil.TypicalModels.BOB;
import static seedu.smartlib.testutil.TypicalModels.HABIT;
import static seedu.smartlib.testutil.TypicalModels.HARRY;
import static seedu.smartlib.testutil.TypicalModels.LIFE;
import static seedu.smartlib.testutil.TypicalModels.RECORD_A;
import static seedu.smartlib.testutil.TypicalModels.RECORD_B;
import static seedu.smartlib.testutil.TypicalModels.SECRET;
import static seedu.smartlib.testutil.TypicalModels.getTypicalSmartLib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.book.Barcode;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.reader.exceptions.DuplicateReaderException;
import seedu.smartlib.model.record.DateBorrowed;
import seedu.smartlib.model.record.Record;
import seedu.smartlib.testutil.ReaderBuilder;

public class SmartLibTest {

    private final SmartLib smartLib = new SmartLib();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), smartLib.getReaderList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> smartLib.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlySmartLib_replacesData() {
        SmartLib newData = getTypicalSmartLib();
        smartLib.resetData(newData);
        assertEquals(newData, smartLib);
    }

    @Test
    public void resetData_withDuplicateReaders_throwsDuplicateReaderException() {
        // Two readers with the same identity fields
        Reader editedAlice = new ReaderBuilder(ALICE)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_VIP)
                .build();
        List<Reader> newReaders = Arrays.asList(ALICE, editedAlice);

        Record record = new Record(
                new Name(VALID_NAME_MAZE),
                new Barcode(Barcode.MAX_VALUE), new Name("Alex Yeoh"), new DateBorrowed("2021-01-22T23:30:00")
        );
        List<Record> newRecords = Arrays.asList(record, record);

        SmartLibStub newData = new SmartLibStub(newRecords, newReaders);

        assertThrows(DuplicateReaderException.class, () -> smartLib.resetData(newData));
    }

    @Test
    public void hasReader_nullReader_throwsNullPointerException() {
    }

    @Test
    public void hasReader_readerNotInSmartLib_returnsFalse() {
        assertFalse(smartLib.hasReader(ALICE));
    }

    @Test
    public void hasReader_readerInSmartLib_returnsTrue() {
        smartLib.addReader(ALICE);
        assertTrue(smartLib.hasReader(ALICE));
    }

    @Test
    public void hasReader_readerWithSameIdentityFieldsInSmartLib_returnsTrue() {
        smartLib.addReader(ALICE);
        Reader editedAlice = new ReaderBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_VIP)
                .build();
        assertTrue(smartLib.hasReader(editedAlice));
    }

    @Test
    public void getReaderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> smartLib.getReaderList().remove(0));
    }

    @Test
    public void getReaderByBarcode() {
        // EP: null barcode
        assertThrows(NullPointerException.class, () -> smartLib.getReaderByBarcode(null));

        // EP: invalid barcode -> returns null
        assertNull(smartLib.getReaderByBarcode(HABIT.getBarcode()));
        assertNull(smartLib.getReaderByBarcode(LIFE.getBarcode()));

        // EP: valid barcode -> returns name of borrower, if any
        ArrayList<Reader> newReaderList = new ArrayList<>();
        newReaderList.add(ALICE);
        newReaderList.add(BENSON);
        smartLib.setReaders(newReaderList);

        ArrayList<Book> newBookList = new ArrayList<>();
        newBookList.add(SECRET);
        smartLib.setBooks(newBookList);

        assertEquals(BENSON, smartLib.getReaderByBarcode(SECRET.getBarcode()));

        // clear data
        smartLib.resetData(new SmartLib());
    }

    @Test
    public void getBooksByName() {
        // EP: null bookname
        assertThrows(NullPointerException.class, () -> smartLib.getBooksByName(null));

        // EP: invalid bookname -> returns empty arraylist
        assertTrue(smartLib.getBooksByName(HABIT.getName()).isEmpty());
        assertTrue(smartLib.getBooksByName(LIFE.getName()).isEmpty());

        // EP: valid bookname -> returns a list of Book objects whose name is specified by bookName, if any
        ArrayList<Book> newBookList = new ArrayList<>();
        newBookList.add(HABIT);
        newBookList.add(LIFE);
        smartLib.setBooks(newBookList);

        ArrayList<Book> expectedList = new ArrayList<>();
        expectedList.add(HABIT);
        assertEquals(expectedList, smartLib.getBooksByName(HABIT.getName()));

        ArrayList<Book> expectedList2 = new ArrayList<>();
        expectedList2.add(LIFE);
        assertEquals(expectedList2, smartLib.getBooksByName(LIFE.getName()));

        // clear data
        smartLib.resetData(new SmartLib());
    }

    @Test
    public void getBookByBarcode() {
        // EP: null barcode
        assertThrows(NullPointerException.class, () -> smartLib.getBookByBarcode(null));

        // EP: invalid barcode -> returns null
        assertNull(smartLib.getBookByBarcode(HABIT.getBarcode()));
        assertNull(smartLib.getBookByBarcode(LIFE.getBarcode()));

        // EP: valid barcode -> returns Book whose barcode is equal to input, if any
        ArrayList<Book> newBookList = new ArrayList<>();
        newBookList.add(SECRET);
        smartLib.setBooks(newBookList);

        assertEquals(SECRET, smartLib.getBookByBarcode(SECRET.getBarcode()));

        // clear data
        smartLib.resetData(new SmartLib());
    }

    @Test
    public void hashCodeTest() {
        SmartLib smartLibCopy = new SmartLib(smartLib);
        int hashcode = smartLib.hashCode();

        // same object, same hashcode
        assertEquals(hashcode, smartLib.hashCode());

        // different object, same data -> same hashcode
        assertEquals(hashcode, smartLibCopy.hashCode());

        // different object, different booklist -> different hashcode
        ArrayList<Book> newBookList = new ArrayList<>();
        newBookList.add(SECRET);
        newBookList.add(HARRY);
        smartLibCopy.setBooks(newBookList);
        assertNotEquals(hashcode, smartLibCopy.hashCode());

        smartLibCopy = new SmartLib(smartLib);
        assertEquals(hashcode, smartLibCopy.hashCode());

        // different object, different readerlist -> different hashcode
        ArrayList<Reader> newReaderList = new ArrayList<>();
        newReaderList.add(ALICE);
        newReaderList.add(BOB);
        smartLibCopy.setReaders(newReaderList);
        assertNotEquals(hashcode, smartLibCopy.hashCode());

        smartLibCopy = new SmartLib(smartLib);
        assertEquals(hashcode, smartLibCopy.hashCode());

        // different object, different recordlist -> different hashcode
        ArrayList<Record> newRecordList = new ArrayList<>();
        newRecordList.add(RECORD_A);
        newRecordList.add(RECORD_B);
        smartLibCopy.setRecords(newRecordList);
        assertNotEquals(hashcode, smartLibCopy.hashCode());
    }

    /**
     * A stub ReadOnlySmartLib whose reader list can violate interface constraints.
     */
    private static class SmartLibStub implements ReadOnlySmartLib {

        private final ObservableList<Book> books = FXCollections.observableArrayList();
        private final ObservableList<Reader> readers = FXCollections.observableArrayList();
        private final ObservableList<Record> records = FXCollections.observableArrayList();

        SmartLibStub(Collection<Record> records, Collection<Reader> readers) {
            this.records.setAll(records);
            this.readers.setAll(readers);
        }

        @Override
        public ObservableList<Book> getBookList() {
            return books;
        }

        @Override
        public ObservableList<Reader> getReaderList() {
            return readers;
        }

        @Override
        public ObservableList<Record> getRecordList() {
            return records;
        }

    }

}
