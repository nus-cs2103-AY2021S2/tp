package seedu.smartlib.model.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.smartlib.testutil.Assert.assertThrows;
import static seedu.smartlib.testutil.TypicalPersons.ALICE;
import static seedu.smartlib.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.smartlib.model.reader.exceptions.DuplicatePersonException;
import seedu.smartlib.model.reader.exceptions.PersonNotFoundException;
import seedu.smartlib.testutil.PersonBuilder;

public class UniqueReaderListTest {

    private final UniqueReaderList uniqueReaderList = new UniqueReaderList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReaderList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueReaderList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueReaderList.addReader(ALICE);
        assertTrue(uniqueReaderList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueReaderList.addReader(ALICE);
        Reader editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueReaderList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReaderList.addReader(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueReaderList.addReader(ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueReaderList.addReader(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReaderList.setPerson(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReaderList.setPerson(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueReaderList.setPerson(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueReaderList.addReader(ALICE);
        uniqueReaderList.setPerson(ALICE, ALICE);
        UniqueReaderList expectedUniqueReaderList = new UniqueReaderList();
        expectedUniqueReaderList.addReader(ALICE);
        assertEquals(expectedUniqueReaderList, uniqueReaderList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueReaderList.addReader(ALICE);
        Reader editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueReaderList.setPerson(ALICE, editedAlice);
        UniqueReaderList expectedUniqueReaderList = new UniqueReaderList();
        expectedUniqueReaderList.addReader(editedAlice);
        assertEquals(expectedUniqueReaderList, uniqueReaderList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueReaderList.addReader(ALICE);
        uniqueReaderList.setPerson(ALICE, BOB);
        UniqueReaderList expectedUniqueReaderList = new UniqueReaderList();
        expectedUniqueReaderList.addReader(BOB);
        assertEquals(expectedUniqueReaderList, uniqueReaderList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueReaderList.addReader(ALICE);
        uniqueReaderList.addReader(BOB);
        assertThrows(DuplicatePersonException.class, () -> uniqueReaderList.setPerson(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReaderList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueReaderList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueReaderList.addReader(ALICE);
        uniqueReaderList.remove(ALICE);
        UniqueReaderList expectedUniqueReaderList = new UniqueReaderList();
        assertEquals(expectedUniqueReaderList, uniqueReaderList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReaderList.setPersons((UniqueReaderList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueReaderList.addReader(ALICE);
        UniqueReaderList expectedUniqueReaderList = new UniqueReaderList();
        expectedUniqueReaderList.addReader(BOB);
        uniqueReaderList.setPersons(expectedUniqueReaderList);
        assertEquals(expectedUniqueReaderList, uniqueReaderList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReaderList.setPersons((List<Reader>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueReaderList.addReader(ALICE);
        List<Reader> readerList = Collections.singletonList(BOB);
        uniqueReaderList.setPersons(readerList);
        UniqueReaderList expectedUniqueReaderList = new UniqueReaderList();
        expectedUniqueReaderList.addReader(BOB);
        assertEquals(expectedUniqueReaderList, uniqueReaderList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Reader> listWithDuplicateReaders = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueReaderList.setPersons(listWithDuplicateReaders));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueReaderList.asUnmodifiableObservableList().remove(0));
    }
}
