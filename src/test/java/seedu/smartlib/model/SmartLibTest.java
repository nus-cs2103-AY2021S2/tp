package seedu.smartlib.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.smartlib.testutil.Assert.assertThrows;
import static seedu.smartlib.testutil.TypicalReaders.ALICE;
import static seedu.smartlib.testutil.TypicalReaders.getTypicalSmartLib;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.reader.exceptions.DuplicateReaderException;
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
        // Two persons with the same identity fields
        Reader editedAlice = new ReaderBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Reader> newReaders = Arrays.asList(ALICE, editedAlice);
        SmartLibStub newData = new SmartLibStub(newReaders);

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
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class SmartLibStub implements ReadOnlySmartLib {
        private final ObservableList<Reader> readers = FXCollections.observableArrayList();

        SmartLibStub(Collection<Reader> readers) {
            this.readers.setAll(readers);
        }

        @Override
        public ObservableList<Reader> getReaderList() {
            return readers;
        }
    }

}
