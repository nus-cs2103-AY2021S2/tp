package fooddiary.model.entry;

import fooddiary.model.entry.exceptions.DuplicateEntryException;
import fooddiary.model.entry.exceptions.EntryNotFoundException;
import fooddiary.testutil.EntryBuilder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static fooddiary.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static fooddiary.logic.commands.CommandTestUtil.VALID_TAG_WESTERN;
import static fooddiary.testutil.Assert.assertThrows;
import static fooddiary.testutil.TypicalEntries.ALICE;
import static fooddiary.testutil.TypicalEntries.BOB;
import static org.junit.jupiter.api.Assertions.*;

public class UniqueEntryListTest {

    private final UniqueEntryList uniqueEntryList = new UniqueEntryList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueEntryList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueEntryList.add(ALICE);
        assertTrue(uniqueEntryList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEntryList.add(ALICE);
        Entry editedAlice = new EntryBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_WESTERN)
                .build();
        assertTrue(uniqueEntryList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueEntryList.add(ALICE);
        assertThrows(DuplicateEntryException.class, () -> uniqueEntryList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.setEntry(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.setEntry(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(EntryNotFoundException.class, () -> uniqueEntryList.setEntry(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueEntryList.add(ALICE);
        uniqueEntryList.setEntry(ALICE, ALICE);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        expectedUniqueEntryList.add(ALICE);
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueEntryList.add(ALICE);
        Entry editedAlice = new EntryBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_WESTERN)
                .build();
        uniqueEntryList.setEntry(ALICE, editedAlice);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        expectedUniqueEntryList.add(editedAlice);
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueEntryList.add(ALICE);
        uniqueEntryList.setEntry(ALICE, BOB);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        expectedUniqueEntryList.add(BOB);
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueEntryList.add(ALICE);
        uniqueEntryList.add(BOB);
        assertThrows(DuplicateEntryException.class, () -> uniqueEntryList.setEntry(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(EntryNotFoundException.class, () -> uniqueEntryList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueEntryList.add(ALICE);
        uniqueEntryList.remove(ALICE);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.setEntry((UniqueEntryList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueEntryList.add(ALICE);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        expectedUniqueEntryList.add(BOB);
        uniqueEntryList.setEntry(expectedUniqueEntryList);
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.setEntry((List<Entry>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueEntryList.add(ALICE);
        List<Entry> entryList = Collections.singletonList(BOB);
        uniqueEntryList.setEntry(entryList);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        expectedUniqueEntryList.add(BOB);
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Entry> listWithDuplicateEntries = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateEntryException.class, () -> uniqueEntryList.setEntry(listWithDuplicateEntries));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueEntryList.asUnmodifiableObservableList().remove(0));
    }
}
