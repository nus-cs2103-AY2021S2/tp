package fooddiary.model.entry;

import static fooddiary.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static fooddiary.logic.commands.CommandTestUtil.VALID_TAG_WESTERN;
import static fooddiary.testutil.Assert.assertThrows;
import static fooddiary.testutil.TypicalEntries.ALICE;
import static fooddiary.testutil.TypicalEntries.BOB;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import fooddiary.model.entry.exceptions.DuplicateEntryException;
import fooddiary.model.entry.exceptions.EntryNotFoundException;
import fooddiary.testutil.EntryBuilder;

public class UniqueEntryListTest {

    private final UniqueEntryList uniqueEntryList = new UniqueEntryList();

    @Test
    public void contains_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.contains(null));
    }

    @Test
    public void contains_entryNotInList_returnsFalse() {
        assertFalse(uniqueEntryList.contains(ALICE));
    }

    @Test
    public void contains_entryInList_returnsTrue() {
        uniqueEntryList.add(ALICE);
        assertTrue(uniqueEntryList.contains(ALICE));
    }

    @Test
    public void contains_entryWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEntryList.add(ALICE);
        Entry editedAlice = new EntryBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_WESTERN)
                .build();
        assertTrue(uniqueEntryList.contains(editedAlice));
    }

    @Test
    public void add_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.add(null));
    }

    @Test
    public void add_duplicateEntry_throwsDuplicateEntryException() {
        uniqueEntryList.add(ALICE);
        assertThrows(DuplicateEntryException.class, () -> uniqueEntryList.add(ALICE));
    }

    @Test
    public void setEntry_nullTargetEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.setEntry(null, ALICE));
    }

    @Test
    public void setEntry_nullEditedEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.setEntry(ALICE, null));
    }

    @Test
    public void setEntry_targetEntryNotInList_throwsEntryNotFoundException() {
        assertThrows(EntryNotFoundException.class, () -> uniqueEntryList.setEntry(ALICE, ALICE));
    }

    @Test
    public void setEntry_editedEntryIsSameEntry_success() {
        uniqueEntryList.add(ALICE);
        uniqueEntryList.setEntry(ALICE, ALICE);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        expectedUniqueEntryList.add(ALICE);
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setEntry_editedEntryHasSameIdentity_success() {
        uniqueEntryList.add(ALICE);
        Entry editedAlice = new EntryBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_WESTERN)
                .build();
        uniqueEntryList.setEntry(ALICE, editedAlice);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        expectedUniqueEntryList.add(editedAlice);
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setEntry_editedEntryHasDifferentIdentity_success() {
        uniqueEntryList.add(ALICE);
        uniqueEntryList.setEntry(ALICE, BOB);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        expectedUniqueEntryList.add(BOB);
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setEntry_editedEntryHasNonUniqueIdentity_throwsDuplicateEntryException() {
        uniqueEntryList.add(ALICE);
        uniqueEntryList.add(BOB);
        assertThrows(DuplicateEntryException.class, () -> uniqueEntryList.setEntry(ALICE, BOB));
    }

    @Test
    public void remove_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.remove(null));
    }

    @Test
    public void remove_entryDoesNotExist_throwsEntryNotFoundException() {
        assertThrows(EntryNotFoundException.class, () -> uniqueEntryList.remove(ALICE));
    }

    @Test
    public void remove_existingEntry_removesEntry() {
        uniqueEntryList.add(ALICE);
        uniqueEntryList.remove(ALICE);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setEntries_nullUniqueEntryList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.setEntry((UniqueEntryList) null));
    }

    @Test
    public void setEntries_uniqueEntryList_replacesOwnListWithProvidedUniqueEntryList() {
        uniqueEntryList.add(ALICE);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        expectedUniqueEntryList.add(BOB);
        uniqueEntryList.setEntry(expectedUniqueEntryList);
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setEntries_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.setEntry((List<Entry>) null));
    }

    @Test
    public void setEntries_list_replacesOwnListWithProvidedList() {
        uniqueEntryList.add(ALICE);
        List<Entry> entryList = Collections.singletonList(BOB);
        uniqueEntryList.setEntry(entryList);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        expectedUniqueEntryList.add(BOB);
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setEntries_listWithDuplicateEntries_throwsDuplicateEntryException() {
        List<Entry> listWithDuplicateEntries = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateEntryException.class, () -> uniqueEntryList.setEntry(listWithDuplicateEntries));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueEntryList.asUnmodifiableObservableList().remove(0));
    }
}
