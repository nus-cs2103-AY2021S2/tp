package seedu.smartlib.model.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.smartlib.testutil.Assert.assertThrows;
import static seedu.smartlib.testutil.TypicalModels.ALICE;
import static seedu.smartlib.testutil.TypicalModels.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.smartlib.model.reader.exceptions.DuplicateReaderException;
import seedu.smartlib.model.reader.exceptions.ReaderNotFoundException;
import seedu.smartlib.testutil.ReaderBuilder;

public class UniqueReaderListTest {

    private final UniqueReaderList uniqueReaderList = new UniqueReaderList();

    @Test
    public void contains_nullReader_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReaderList.contains(null));
    }

    @Test
    public void contains_readerNotInList_returnsFalse() {
        assertFalse(uniqueReaderList.contains(ALICE));
    }

    @Test
    public void contains_readerInList_returnsTrue() {
        uniqueReaderList.addReader(ALICE);
        assertTrue(uniqueReaderList.contains(ALICE));
    }

    @Test
    public void contains_readerWithSameIdentityFieldsInList_returnsTrue() {
        uniqueReaderList.addReader(ALICE);
        Reader editedAlice = new ReaderBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueReaderList.contains(editedAlice));
    }

    @Test
    public void add_nullReader_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReaderList.addReader(null));
    }

    @Test
    public void add_duplicateReader_throwsDuplicateReaderException() {
        uniqueReaderList.addReader(ALICE);
        assertThrows(DuplicateReaderException.class, () -> uniqueReaderList.addReader(ALICE));
    }

    @Test
    public void setReader_nullTargetReader_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReaderList.setReader(null, ALICE));
    }

    @Test
    public void setReader_nullEditedReader_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReaderList.setReader(ALICE, null));
    }

    @Test
    public void setReader_targetReaderNotInList_throwsReaderNotFoundException() {
        assertThrows(ReaderNotFoundException.class, () -> uniqueReaderList.setReader(ALICE, ALICE));
    }

    @Test
    public void setReader_editedReaderIsSameReader_success() {
        uniqueReaderList.addReader(ALICE);
        uniqueReaderList.setReader(ALICE, ALICE);
        UniqueReaderList expectedUniqueReaderList = new UniqueReaderList();
        expectedUniqueReaderList.addReader(ALICE);
        assertEquals(expectedUniqueReaderList, uniqueReaderList);
    }

    @Test
    public void setReader_editedReaderHasSameIdentity_success() {
        uniqueReaderList.addReader(ALICE);
        Reader editedAlice = new ReaderBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueReaderList.setReader(ALICE, editedAlice);
        UniqueReaderList expectedUniqueReaderList = new UniqueReaderList();
        expectedUniqueReaderList.addReader(editedAlice);
        assertEquals(expectedUniqueReaderList, uniqueReaderList);
    }

    @Test
    public void setReader_editedReaderHasDifferentIdentity_success() {
        uniqueReaderList.addReader(ALICE);
        uniqueReaderList.setReader(ALICE, BOB);
        UniqueReaderList expectedUniqueReaderList = new UniqueReaderList();
        expectedUniqueReaderList.addReader(BOB);
        assertEquals(expectedUniqueReaderList, uniqueReaderList);
    }

    @Test
    public void setReader_editedReaderHasNonUniqueIdentity_throwsDuplicateReaderException() {
        uniqueReaderList.addReader(ALICE);
        uniqueReaderList.addReader(BOB);
        assertThrows(DuplicateReaderException.class, () -> uniqueReaderList.setReader(ALICE, BOB));
    }

    @Test
    public void remove_nullReader_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReaderList.remove(null));
    }

    @Test
    public void remove_readerDoesNotExist_throwsReaderNotFoundException() {
        assertThrows(ReaderNotFoundException.class, () -> uniqueReaderList.remove(ALICE));
    }

    @Test
    public void remove_existingReader_removesReader() {
        uniqueReaderList.addReader(ALICE);
        uniqueReaderList.remove(ALICE);
        UniqueReaderList expectedUniqueReaderList = new UniqueReaderList();
        assertEquals(expectedUniqueReaderList, uniqueReaderList);
    }

    @Test
    public void setReaders_nullUniqueReaderList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReaderList.setReaders((UniqueReaderList) null));
    }

    @Test
    public void setReaders_uniqueReaderList_replacesOwnListWithProvidedUniqueReaderList() {
        uniqueReaderList.addReader(ALICE);
        UniqueReaderList expectedUniqueReaderList = new UniqueReaderList();
        expectedUniqueReaderList.addReader(BOB);
        uniqueReaderList.setReaders(expectedUniqueReaderList);
        assertEquals(expectedUniqueReaderList, uniqueReaderList);
    }

    @Test
    public void setReaders_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReaderList.setReaders((List<Reader>) null));
    }

    @Test
    public void setReaders_list_replacesOwnListWithProvidedList() {
        uniqueReaderList.addReader(ALICE);
        List<Reader> readerList = Collections.singletonList(BOB);
        uniqueReaderList.setReaders(readerList);
        UniqueReaderList expectedUniqueReaderList = new UniqueReaderList();
        expectedUniqueReaderList.addReader(BOB);
        assertEquals(expectedUniqueReaderList, uniqueReaderList);
    }

    @Test
    public void setReaders_listWithDuplicateReaders_throwsDuplicateReaderException() {
        List<Reader> listWithDuplicateReaders = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateReaderException.class, () -> uniqueReaderList.setReaders(listWithDuplicateReaders));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueReaderList.asUnmodifiableObservableList().remove(0));
    }
}
