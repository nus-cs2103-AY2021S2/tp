package seedu.smartlib.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.smartlib.testutil.Assert.assertThrows;
import static seedu.smartlib.testutil.TypicalModels.ALICE;
import static seedu.smartlib.testutil.TypicalModels.getTypicalSmartLib;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.smartlib.commons.core.name.Name;
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
        Reader editedAlice = new ReaderBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Reader> newReaders = Arrays.asList(ALICE, editedAlice);
        Record record = new Record(new Name("Cloud Atlas"), new Name("Alex Yeoh"), new DateBorrowed("2020-11-23"));
        List<Record> newRecords = Arrays.asList(record, record);
        SmartLibStub newData = new SmartLibStub(newRecords, newReaders);

        assertThrows(DuplicateReaderException.class, () -> smartLib.resetData(newData));
    }

    @Test
    public void hasReader_nullReader_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> smartLib.hasReader(null));
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
        Reader editedAlice = new ReaderBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(smartLib.hasReader(editedAlice));
    }

    @Test
    public void getReaderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> smartLib.getReaderList().remove(0));
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
